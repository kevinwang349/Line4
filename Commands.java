package pkg;

// Java imports
import java.util.Scanner;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// JDA imports
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
// JSON imports, used to read JSON objects
import org.json.JSONObject;
import org.json.JSONArray;

public class Commands extends ListenerAdapter {

	public static JDA client; // bot client
	public static Color purple; // default color for embeds
	// dead flag: if you tell bot to "die", it goes offline and stops responding until you "revive" it
	public static boolean dead=false;
	// Commands constructor
	public Commands() {
		// TODO Auto-generated constructor stub
		// Set the client and the default color to the ones in Line4.java
		client=Line4.client;
		purple=Line4.purple;
	}

	// Main event listener, called when any message is received by the bot
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		String[] args=event.getMessage().getContentRaw().split("\\s+"); // get and split message content
		String command=args[0]; // get the first "argument" aka the command
		// If the bot is "dead", you can "revive" it
		if(dead&&command.equalsIgnoreCase(Line4.prefix+"revive")){
			// Set its status to online and the default "Watching trains"
			client.getPresence().setStatus(OnlineStatus.ONLINE);
			client.getPresence().setActivity(Activity.watching("trains"));
			dead=false; // set the dead flag to false
		}
		// If the bot is dead, don't respond to any command other than revive
		else if(!dead){
			String content=event.getMessage().getContentRaw(); // get the message's contents as a whole string
			// If the message contains a bot ping and it wasn't sent by a bot, send a message
			if((content.contains("<@!757698537914892288>")||content.contains("<@&757698537914892288>")
					||content.contains("<@757698537914892288>")
					||content.contains("@everyone")||content.contains("@here"))
					&&!event.getMessage().getAuthor().isBot()){
				String ping=event.getAuthor().getId()+""; // ping the message author
				// Send a message
				event.getChannel().sendMessage("<@!"+ping+"> , whatcha pinging me for?"
						+ " Don't make me spam everyones like someone else "
						+ "*cough* ||Line 3|| *cough* I know.").queue();
			}
			// Basic ping/pong
			else if(command.equalsIgnoreCase(Line4.prefix+"ping")){
				try {
					event.getChannel().sendTyping().queue(); // make the bot "type" before sending the message
					Thread.sleep(1000); // wait 1000 milliseconds (1 second)
					event.getChannel().sendMessage("pong!").queue(); // send "pong"
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// invite command: outsourced in invite.java
			else if(command.equalsIgnoreCase(Line4.prefix+"invite")){
				try {
					invite.run(event); // execute the run command in invite.java
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// set status to idle
			else if(command.equalsIgnoreCase(Line4.prefix+"setidle")){
				client.getPresence().setStatus(OnlineStatus.IDLE);
			}
			// set status to online
			else if(command.equalsIgnoreCase(Line4.prefix+"setonline")){
				client.getPresence().setStatus(OnlineStatus.ONLINE);
			}
			// set status to do not disturb or dnd
			else if(command.equalsIgnoreCase(Line4.prefix+"setdnd")
					||command.equalsIgnoreCase(Line4.prefix+"setdonotdisturb")){
				client.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			}
			// "die" command: sets the bot's status to offline and activates dead flag
			else if(command.equalsIgnoreCase(Line4.prefix+"die")
					||command.equalsIgnoreCase(Line4.prefix+"mourir")
					||command.equalsIgnoreCase(Line4.prefix+"sterben")
					||command.equalsIgnoreCase(Line4.prefix+"setdead")
					||command.equalsIgnoreCase(Line4.prefix+"setoffline")){
				client.getPresence().setStatus(OnlineStatus.OFFLINE);
				dead=true;
			}
			// custom status commands, run SetStatus.java to handle those
			else if(command.equalsIgnoreCase(Line4.prefix+"setstreaming")
					||command.equalsIgnoreCase(Line4.prefix+"setstream")
					||command.equalsIgnoreCase(Line4.prefix+"setplaying")
					||command.equalsIgnoreCase(Line4.prefix+"setgame")
					||command.equalsIgnoreCase(Line4.prefix+"setlistening")
					||command.equalsIgnoreCase(Line4.prefix+"setlisten")
					||command.equalsIgnoreCase(Line4.prefix+"setwatching")
					||command.equalsIgnoreCase(Line4.prefix+"setwatch")
					||command.equalsIgnoreCase(Line4.prefix+"resetstatus")){
				SetStatus.run(event);
			}
			// basic say/echo command
			else if(command.equalsIgnoreCase(Line4.prefix+"say")){
				if(event.getMessage().getContentRaw().length()>4){
					event.getChannel().sendMessage(event.getMessage().getContentRaw().substring(5)).queue();
				}else{
					event.getChannel().sendMessage("Tell me something to say!").queue();
				}
			}
			// react with a pig
			else if(command.equalsIgnoreCase(Line4.prefix+"pig")){
				event.getMessage().addReaction("🐷");
			}
			// react with a (quag)pog
			else if(command.equalsIgnoreCase(Line4.prefix+"pog")){
				event.getMessage().addReaction("749302905374376037");
			}
			// send a help embed
			else if(command.equalsIgnoreCase(Line4.prefix+"help")
					||command.equalsIgnoreCase(Line4.prefix+"info")){
				EmbedBuilder b=new EmbedBuilder(); // create the embed
				// Add 2 fields of basic help info
				b.addField("Why do we have *another* bot made by a Fiona wannabe?","Hi there!"
						+ " I'm just another useless bot made because my creator had nothing better to do"
						+ " with his pointless life. I hope I make someone's day a little better!",false);
				b.addField("Basic features","I can't really do anything useful as of now.\n"
						+ "Fun fact: Unlike my buddy Line 3 Scarborough, who was made using Discord.js,"
						+ " I was coded using a Java JDA for Discord.",false);
				b.addField(":upside_down:","Enjoy!",false);
				b.setTitle("Bot info"); // set the title to "Bot info"
				b.setAuthor("Made by Kevin W | aka. Line 5 Eglinton#1345"); // set the author
				// set description
				b.setDescription("Another random, useless bot to do more random, useless things!");
				// set footer to "Requested by example#0001" (the tag of the user whosent the command)
				b.setFooter("Requested by "+event.getAuthor().getAsTag());
				b.setColor(purple); // set the color to the default purple
				event.getChannel().sendMessage(b.build()).queue(); // send the embed
			}else if(command.equalsIgnoreCase(Line4.prefix+"transit")){
				try {
					String s=""; // accumulator string to collect the entire contents of the file
					// Get transitinfo.json
					File file=new File("External Files/transitinfo.json");
					Scanner sc=new Scanner(file); // create Scanner to read from the file
					// Read the contents of the file into the accumulator string s
					while(sc.hasNext()){
						s+=sc.nextLine();
					}
					sc.close(); // close Scanner
					JSONArray json=new JSONArray(s); // create JSON array from the file's contents
					// Generate random number, choose randomly from one of the JSON array's elements
					int num=(int)(Math.random()*json.length());
					JSONObject info=new JSONObject(json.get(num)+""); // get a random element
					EmbedBuilder b=new EmbedBuilder(); // create the embed
					// Add random facts field, fill it out with info from the JSON object
					b.addField("Random facts: ","Model: "+info.getString("model")+"\nTransit agency: "+
							info.getString("company")+"\nBuilt by: "+info.getString("built"),false);
					// set the title and image with info from the JSON object
					b.setTitle(info.getString("title"));
					b.setImage(info.getString("img"));
					// set the footer using the user's tag and info from the JSON object
					b.setFooter(event.getAuthor().getAsTag()+" ● "+info.getString("src"));
					b.setColor(purple); // set color to default purple
					event.getChannel().sendMessage(b.build()).queue(); // send the embed
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(command.equalsIgnoreCase(Line4.prefix+"duck")){
				try {
					URL url=new URL("https://random-d.uk/api/quack"); // get random-d.uk's API endpoint
					// Open an HttpURLConnection from the URL
					HttpURLConnection connection= (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET"); // set request to a GET request
					// Create BufferedReader to read whatever the API returns
					BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuffer response=new StringBuffer(); // string accumulator to store return data
					String readLine=null; // each individual line of the file
					// Read the data that the API returns
					while((readLine=in.readLine())!=null){
						response.append(readLine);
					}
					in.close(); // close BufferedReader
					String s=response.toString(); // convert the StringBuffer to a regular string
					JSONObject json=new JSONObject(s); // create JSON object from API data
					EmbedBuilder b=new EmbedBuilder(); // create embed
					b.setTitle("Quack quack!"); // set the title
					b.setImage(json.getString("url")); // set the image with JSON data
					// set the footer to the user's tag and "Powered by random-d.uk"
					b.setFooter(event.getAuthor().getAsTag()+" ●  Powered by random-d.uk");
					b.setColor(purple); // set color to default purple
					event.getChannel().sendMessage(b.build()).queue(); // send the embed
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}