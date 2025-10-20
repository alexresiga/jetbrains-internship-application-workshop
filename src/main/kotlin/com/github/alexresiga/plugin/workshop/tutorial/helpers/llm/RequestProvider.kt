package com.github.alexresiga.plugin.workshop.tutorial.helpers.llm

import com.github.alexresiga.plugin.workshop.tutorial.helpers.services.HttpClientService
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.platform.ide.progress.withBackgroundProgress

private const val GPT_4_TURBO = "gpt-4-turbo-preview"

@Service(Service.Level.PROJECT)
internal class RequestProvider(private val project: Project) {

    companion object {
        fun getInstance(project: Project): RequestProvider = project.service<RequestProvider>()
    }

    private val prompt =
        """
            You are an experienced Kotlin developer with comprehensive knowledge in code quality.
            Write idiomatic, concise, short and readable Kotlin code.
            Always prefer immutable data structures over mutable ones, unless you necessarily have to.
            Your task is to migrate Java code to idiomatic Kotlin code. 
            Don't output any piece of text that is not related to the code block.
            Don't create any new classes or methods.
            The input code block is a single string.
            The output code block should be a single string.
            Format the answer in a html code block.
            Retain any documentation and comments from the original code.
            Don't output the language tag such as kotlin, html, java.
            Here is the Java code block:
        """

    suspend fun sendRequest(userMessage: String): String = withBackgroundProgress(project, "OpenAI Request..", true) {
        val request = prepareRequest(prompt + userMessage)
        HttpClientService.getInstance().submit(request)
    }

    private fun prepareRequest(userMessage: String): OpenAIRequest {
        val openAIRequest = OpenAIRequest(
            model = GPT_4_TURBO,
            messages = listOf(ResponseMessage("user", userMessage))
        )
        return openAIRequest
    }
}