Develop an IntelliJ IDEA plugin that allows users to select Java code blocks, view their equivalent Kotlin code, and compare their length or token count between the two versions.

## Required functionality
- The plugin should allow users to select a block of Java code in an editor tab
- Provide an action via the context menu (the yellow lightbulb) to trigger the action
- When triggered, convert the selected Java code to its Kotlin equivalent (using OpenAI API calls)
- Display the resulting Kotlin code in a popup window
- Compute and display the text length difference percentage

## Additional features
- Instead of displaying the text length comparison, compute and display the token count reduction percentage
- Provide a way to cache results for code blocks and reuse past conversions, since OpenAI API calls can be slow 

### Bonus feature
- Convert the code: provide the users with an action to convert entire Java files to Kotlin files

