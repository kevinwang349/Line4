package pkg;

//Various imports
import java.awt.Color;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Line4 {
	
	public static String prefix="#"; // bot prefix
	public static Color purple=new Color(179,0,179); // default color (purple) for embeds
	public static JDA client; // bot client

	// Main method
	public static void main(String[] args) throws LoginException {
		// TODO Auto-generated method stub

		client = JDABuilder.createDefault("no").build(); // login as a bot user
		client.getPresence().setStatus(OnlineStatus.IDLE); // set the bot's status to idle
		client.getPresence().setActivity(Activity.watching("trains")); // set the bot's status to "Watching trains"
		
		client.addEventListener(new Commands()); // add event listener, so the bot can react to commands
	}

}
