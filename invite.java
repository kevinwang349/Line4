package pkg;

// JDA import, import the Message Received event
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class invite {
	
	public static void run(GuildMessageReceivedEvent event) throws InterruptedException{
		event.getChannel().sendTyping().queue(); // make the bot "type" before sending the message
		Thread.sleep(1000); // wait for 1000 milliseconds (1 second)
		// Send the bot's invite link
		event.getChannel().sendMessage("Here's my invite link: https://discord.com/oauth2/"
						+ "authorize?client_id=757698537914892288&scope=bot&permissions=523328").queue();
	}

}
