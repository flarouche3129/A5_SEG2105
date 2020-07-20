# A5_SEG2105
SEG2105 final project :  Billy, the Music Bot !

Hello, and welcome to the Billy, the Music Bot project!

Billy is a discord bot that allows the user to play the requested tracks in the discord channel he is currently in. Billy isn't very complex since I've only had a week to do it and I had to learn how APIs work from scratch, but my goals for Billy were fairly simple. I wanted to be able to use Billy to :

- Be able to request a track and have it played in my discord channel
- Be able to use either a YouTube URL or the name of the song for the !play command
- Be able to pause the currently playing track, and resume it
- Be able to stop Billy completly and close the connection with the discord channel
- Be able to change the volume of the track currently playing
- Be able to see the current volume of the song playing
- Be able to use Billy very easily, even if I'm not very accustomed with discord bots in general

As you can see, Billy is very simple but works quite well. Some tests you could do are : 

- Try to do !help, to see all the available commands for Billy
- Try to do !ping, to see if the bot is responding
- Try to do !play <YouTube URL/song request> while NOT in a discord channel, to see if the bot responds correctly
- Try to do !play while IN a discord channel, to see if the bot plays the song requested
- Try to do !pause, to see if the song currently playing is being paused or not
- Try to do !play after pausing the song, to see if the song get's resumed or not
- Try to do !stop while a song is playing, to see if Billy stops the song currently playing and leaves the discord channel
- Try to do !play without a request when no song is being played, to see if Billy reacts correctly
- Try to do !volume while a song is playing, to see if Billy responds with the current volume
- Try to do !volume <int between 50 and 150>, to see if Billy sets the volume to the one requested
- Try to set a volume not between 50 and 150, to see if Billy tells you that the volume needs to be between 50 and 150

The main architectural pattern I've used for Billy is the MVC pattern, since Billy is very flexible. Many classes could be removed without breaking Billy completly, and many new components (e.g new commands/features) could be added very easiy. To do that, you'd only need to follow the same pattern I've used for all my commands, or you could even add a way to queue songs one after the other. I used JDA (Java Discord API - https://github.com/DV8FromTheWorld/JDA/wiki ) to handle the song request and such as well as Lavaplayer (https://github.com/sedmelluq/lavaplayer), which is an audio player library for Discord to download the song from YouTube. Finally, I have used Maven, a software project management and comprehension tool, mainly to make my dependencies a lot more clearer and easy to find. 

To find everything, all you have to do is go in src -> main -> java -> com.larouche.felix.billythemusicbot and you'll find everything you need. You can also find in this repo a class diagram to help understand how Billy works (Package billythemusicbot.png or billythemusicbot.uml). 

To run Billy, you obviously need to install Discord (https://discord.com/new/download), and set up an account. If you already have a Discord and an account, you need to have at least one server where you are an admin since you'll need to invite Billy to your discord server. You will then need to invite Billy using this link (https://discordapp.com/oauth2/authorize?client_id=733397235877937162&scope=bot&permissions=36752512). To work, Billy will need a bot token. In the Bot class, there is a System.getenv variable called BOT_TOKEN where you'll need to put the bot token provided through the assignement submission where the "BOT_TOKEN" is. Then, you only need to run the main method and Billy should be able to work. You can do **!help** and Billy will message you privately with all the information you need. If anything, don't hesitate to message me at **flaro058@uottawa.ca**. 