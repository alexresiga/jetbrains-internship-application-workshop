package com.github.alexresiga.plugin.workshop.tutorial.startup

import com.github.alexresiga.plugin.workshop.tutorial.helpers.llm.RequestProvider
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class MyProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
        try {
            RequestProvider.getInstance(project).sendRequest("")
            thisLogger().warn("OpenAI test request has succeeded!")
        } catch (e: Exception) {
            thisLogger().error(
                """OpenAI test request has FAILED. 
                    |Make sure you set OPENAI_API_KEY in the src/main/resources/.env file!
                    |${e.message}""".trimMargin()
            )
        }
    }
}