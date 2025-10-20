package com.github.alexresiga.plugin.workshop.tutorial.helpers.services

import com.github.alexresiga.plugin.workshop.tutorial.helpers.CredentialsManager
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import com.github.alexresiga.plugin.workshop.tutorial.helpers.llm.OpenAIRequest
import com.github.alexresiga.plugin.workshop.tutorial.helpers.llm.OpenAIResponse
import java.io.IOException

private const val openAiApiUrl = "https://api.openai.com/v1/chat/completions"

@Service
class HttpClientService : Disposable {
    companion object {
        fun getInstance(): HttpClientService = service()
    }

    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            retryOnExceptionIf { _, cause -> cause is HttpRequestTimeoutException }
            exponentialDelay()
        }
    }

    suspend fun submit(requestData: OpenAIRequest): String {
        val openApiKey = CredentialsManager.getInstance().getOpenAiApiKey()
        if (openApiKey.isNullOrEmpty())
            throw EmptyOrInvalidApiKeyException("OpenAI API Key is not provided. Make sure to set OPENAI_API_KEY in the src/main/resources/.env file")

        return client.let { client ->
            val request = prepareOpenAIRequest(openApiKey, requestData)
            client.prepareRequest(request).execute {
                parseHttpResponse(it)
            }
        }
    }

    private fun prepareOpenAIRequest(
        openApiKey: String,
        requestData: OpenAIRequest,
    ) = request {
        url(openAiApiUrl)
        accept(ContentType.Text.EventStream)
        contentType(ContentType.Application.Json)
        bearerAuth(openApiKey)
        setBody(requestData)
        method = HttpMethod.Post
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun parseHttpResponse(httpResponse: HttpResponse): String {
        if (httpResponse.status != HttpStatusCode.OK) {
            throw IOException("HTTP response code: ${httpResponse.status}")
        }

        val json = Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

        return httpResponse.bodyAsChannel().toInputStream().use { inputStream ->
            val openAIResponse = json.decodeFromStream<OpenAIResponse>(inputStream)
            val choice = openAIResponse.choices.firstOrNull()
            choice?.message?.content?.replace("`", "") ?: ""
        }
    }

    override fun dispose() {
        client.close()
    }
}

class EmptyOrInvalidApiKeyException(message: String) : Exception(message)