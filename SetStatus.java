package sure;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SetStatus {

	public static void run(GuildMessageReceivedEvent event) {
		JDA client=Line4.client;
		String[] args=event.getMessage().getContentRaw().split("\\s+");
		String command=args[0];
		if(command.equalsIgnoreCase(Line4.prefix+"resetstatus")){
			client.getPresence().setActivity(Activity.watching("trains"));
		}else if(command.equalsIgnoreCase(Line4.prefix+"setstreaming")
				||command.equalsIgnoreCase(Line4.prefix+"setstream")){
			String status="lmao";
			if(args.length>1){
				status=args[1];
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			client.getPresence().setActivity(
					Activity.streaming(status, "https://www.youtube.com/watch?v=rvrZJ5C_Nwg&t=2m22s"));
		}else if(command.equalsIgnoreCase(Line4.prefix+"setplaying")
				||command.equalsIgnoreCase(Line4.prefix+"setgame")){
			String status="a game";
			if(args.length>1){
				status=args[1];
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			client.getPresence().setActivity(
					Activity.playing(status));
		}else if(command.equalsIgnoreCase(Line4.prefix+"setlistening")
				||command.equalsIgnoreCase(Line4.prefix+"setlisten")){
			String status="something";
			if(args.length>1){
				status=args[1];
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			client.getPresence().setActivity(
					Activity.listening(status));
		}else if(command.equalsIgnoreCase(Line4.prefix+"setwatching")
				||command.equalsIgnoreCase(Line4.prefix+"setwatch")){
			String status="subway trains";
			if(args.length>1){
				status=args[1];
				for(int i=2;i<args.length;i++){
					status+=" "+args[i];
				}
			}
			client.getPresence().setActivity(
					Activity.watching(status));
		}

	}

}
