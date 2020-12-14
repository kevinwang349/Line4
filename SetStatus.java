package pkg;

// Various JDA imports
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SetStatus {

	public static void run(GuildMessageReceivedEvent event) {
		JDA client=Line4.client; // get the client from Line4.java
		String[] args=event.getMessage().getContentRaw().split("\\s+"); // get and split message content
		String command=args[0]; // get the first "argument" aka the command
		// Reset status to "Watching trains"
		if(command.equalsIgnoreCase(Line4.prefix+"resetstatus")){
			client.getPresence().setActivity(Activity.watching("trains"));
		}
		// Set status to "Streaming" something, complete with a URL
		else if(command.equalsIgnoreCase(Line4.prefix+"setstreaming")
				||command.equalsIgnoreCase(Line4.prefix+"setstream")){
			String status="lmao"; // default status, if no custom status is provided
			// If a custom status is provided, set the status string to the custom status
			if(args.length>1){
				status=args[1]; // reset the string to the first word of the custom status
				// Add the other words of the custom status
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			// Set the status to "Streaming" whatever, including a URL to redirect to
			client.getPresence().setActivity(
					Activity.streaming(status, "https://www.youtube.com/watch?v=rvrZJ5C_Nwg&t=2m22s"));
		}
		// Set status to "Playing" something
		else if(command.equalsIgnoreCase(Line4.prefix+"setplaying")
				||command.equalsIgnoreCase(Line4.prefix+"setgame")){
			String status="a game"; // default status, if no custom status is provided
			// If a custom status is provided, set the status string to the custom status
			if(args.length>1){
				status=args[1]; // reset the string to the first word of the custom status
				// Add the other words of the custom status
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			// Set the status to "Playing" whatever
			client.getPresence().setActivity(Activity.playing(status));
		}
		// Set status to "Listening to" something
		else if(command.equalsIgnoreCase(Line4.prefix+"setlistening")
				||command.equalsIgnoreCase(Line4.prefix+"setlisten")){
			String status="something"; // default status, if no custom status is provided
			// If a custom status is provided, set the status string to the custom status
			if(args.length>1){
				status=args[1]; // reset the string to the first word of the custom status
				// Add the other words of the custom status
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			// Set the status to "Listening to" whatever
			client.getPresence().setActivity(Activity.listening(status));
		}
		// Set status to "Watching" something
		else if(command.equalsIgnoreCase(Line4.prefix+"setwatching")
				||command.equalsIgnoreCase(Line4.prefix+"setwatch")){
			String status="subway trains"; // default status, if no custom status is provided
			// If a custom status is provided, set the status string to the custom status
			if(args.length>1){
				status=args[1]; // reset the string to the first word of the custom status
				// Add the other words of the custom status
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			// Set the status to "Watching" whatever
			client.getPresence().setActivity(Activity.watching(status));
		}

	}

}
