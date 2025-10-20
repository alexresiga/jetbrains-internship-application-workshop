package com.github.alexresiga.plugin.workshop.tutorial.helpers

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import io.github.cdimascio.dotenv.dotenv


private const val OPEN_API_KEY = "OPENAI_API_KEY"

/**
 * [See documentation on persisting sensitive data](https://plugins.jetbrains.com/docs/intellij/persisting-sensitive-data.html)
 */
@Service(Service.Level.APP)
class CredentialsManager {
    private val dotenv = dotenv()

    /** Set the [OPEN_API_KEY] key in src/main/resources/.env */
    fun getOpenAiApiKey(): String? = dotenv[OPEN_API_KEY]

    companion object {
        fun getInstance(): CredentialsManager = service<CredentialsManager>()
    }

}