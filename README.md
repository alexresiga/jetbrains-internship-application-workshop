# Internship Application Workshop

<!-- Plugin description -->
Kotlin Sample Plugin for JetBrains Internship Application Workshop. You can find the task description in the [task.md](task.md) file.
<!-- Plugin description end -->

## Requirements

The plugin requires Java 17 and [IntelliJ IDEA 2025.1](https://www.jetbrains.com/idea/download/) or higher to work.

The plugin requires an OpenAI API key. The key will be provided during the workshop. 

You need to create a `.env` file inside of `src/main/resources/`. You need to set `OPENAI_API_KEY=<your-key>` in the `src/main/resources/.env` file.

## Troubleshooting

- [Settings | Build, Execution, Deployment | Build Tools | Gradle](jetbrains://idea/settings?name=Build%2C+Execution%2C+Deployment--Build+Tools--Gradle) - in case IJ complains about Gradle JDK missing or not being found
  - change to some existing JDK (preferably 17+ from your machine or download one from that dropdown)
  - restart "Sync Gradle Project" from the Build toolwindow | Sync | left side buttons

## Services
There are helper methods to work with OpenAI API. Feel free to modify them as you find fit.

- [HttpClientService](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/services/HttpClientService.kt): basic http client service
- [RequestProvider](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/RequestProvider.kt): provides a request to OpenAI API
- [OpenAIRequest](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/OpenAIRequest.kt): OpenAI API request model
- [OpenAIResponse](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/helpers/llm/OpenAIResponse.kt): OpenAI API response model

Please see [MyProjectActivity.kt](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/startup/MyProjectActivity.kt) for an example of how to make an OpenAI API request.

Please take a look at [MyProjectActivity.kt](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/startup/MyProjectActivity.kt) and 
[MyToolWindowFactory.kt](src/main/kotlin/com/github/alexresiga/plugin/workshop/tutorial/toolWindow/MyToolWindowFactory.kt) and their usages inside
[plugin.xml](src/main/resources/META-INF/plugin.xml) to see how we can register custom objects in the plugin.

## Useful Materials 

Try looking up the following resources to learn more about the IntelliJ Platform and Kotlin:
- [Platform Plugin SDK documentation](https://plugins.jetbrains.com/docs/intellij/welcome.html)
    - [Intentions/Actions](https://plugins.jetbrains.com/docs/intellij/code-intentions.html)
    - [Program Structure Interface (PSI)](https://plugins.jetbrains.com/docs/intellij/psi.html)
- [Kotlin Coroutines in the IntelliJ Platform](https://plugins.jetbrains.com/docs/intellij/coroutines.html)
    - [Coroutine Scopes](https://plugins.jetbrains.com/docs/intellij/coroutine-scopes.html)
    - [Launching Coroutines](https://plugins.jetbrains.com/docs/intellij/launching-coroutines.html)
- (Later) [JetBrains Academy courses](https://www.jetbrains.com/academy/):
    - [IDE Plugin Development Course](https://plugins.jetbrains.com/plugin/25398-ide-plugin-development-course) - more info about working with the PSI
    - [Coroutines and channels](https://plugins.jetbrains.com/plugin/23312-coroutines-and-channels) - a course about using coroutines in IntelliJ IDEA


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

## Resources

- You can use [this repository](https://github.com/alexresiga/KotlinJavaProject/) as a test project for your plugin. Open it in the IntelliJ IDEA opened by the `Run Plugin` run configuration.
---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
