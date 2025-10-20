# jetbrains-internship-application-workshop

<!-- Plugin description -->
IntelliJ IDEA plugin that allows users to select Java code blocks, view their equivalent Kotlin code, and analyze the difference in token count between the two versions.
<!-- Plugin description end -->

## Requirements

The plugin requires Java 17 and IntelliJ IDEA 2025.1 or higher to work.

The plugin requires an OpenAI API key. You need to create a `.env` file inside of `src/main/resources/`. You need to set `OPENAI_API_KEY=<your-key>` in the `src/main/resources/.env` file.

## Services
There are helper methods to work with OpenAI API. Feel free to modify them as you find fit.

- [HttpClientService](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/services/HttpClientService.kt): basic http client service
- [RequestProvider](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/RequestProvider.kt): provides a request to OpenAI API
- [OpenAIRequest](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/OpenAIRequest.kt): OpenAI API request model
- [OpenAIResponse](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/OpenAIResponse.kt): OpenAI API response model

## Running and Building

Available `Run configurations` can be found in `Gradle` → `Run Configurations`:

- Most useful one:`Run Plugin`:
  Runs [`:runIde`](https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html#tasks-runide) task. This will open a running IntelliJ IDEA with the plugin installed. Use
  the *Debug* icon for plugin debugging.
- `Build Plugin`:
  Runs [`:buildPlugin`](https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html#tasks-buildplugin)
  task. Build the plugin and saves `.zip`
  archive with
  it in `build/distributions` folder.
- `Run Tests`: Runs [`:test`](https://docs.gradle.org/current/userguide/java_plugin.html#lifecycle_tasks) task.

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
