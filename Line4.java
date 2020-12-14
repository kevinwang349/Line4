package sure;

import java.awt.Color;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Line4 {
	
	public static String prefix="#";
	public static Color purple=new Color(179,0,179);
	public static JDA client;

	// Main method
	public static void main(String[] args) throws LoginException {
		// TODO Auto-generated method stub

		client = JDABuilder.createDefault("NzU3Njk4NTM3OTE0ODkyMjg4.X2kLwA.YlwciaHhs41SHCmqjP8kfwzeNZM").build();
		client.getPresence().setStatus(OnlineStatus.IDLE);
		client.getPresence().setActivity(Activity.watching("trains"));
		
		client.addEventListener(new Commands());
	}

}
