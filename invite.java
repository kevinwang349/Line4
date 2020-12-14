package sure;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class invite {
	
	public static void run(GuildMessageReceivedEvent event){
		event.getChannel().sendTyping().queue();
		event.getChannel().sendMessage("Here's my invite link: https://discord.com/oauth2/"
						+ "authorize?client_id=757698537914892288&scope=bot&permissions=523328").queue();
	}

}
