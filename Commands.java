package sure;

import java.util.Scanner;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;
import org.json.JSONArray;

public class Commands extends ListenerAdapter {

	public static JDA client;
	public static Color purple;
	public static boolean dead=false;
	public Commands() {
		// TODO Auto-generated constructor stub
		client=Line4.client;
		purple=Line4.purple;
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		String[] args=event.getMessage().getContentRaw().split("\\s+");
		String command=args[0];
		System.out.println(event.getMessage().getContentRaw());
		if(dead&&command.equalsIgnoreCase(Line4.prefix+"revive")){
			client.getPresence().setStatus(OnlineStatus.ONLINE);
			client.getPresence().setActivity(Activity.watching("trains"));
			dead=false;
		}else if(!dead){
			if((command.contains("<@!757698537914892288>")||command.contains("<@&757698537914892288>")
					||command.contains("<@757698537914892288>")
					||command.contains("@everyone")||command.contains("@here"))
					&&!event.getMessage().getAuthor().isBot()){
				String ping=event.getAuthor().getId()+"";
				event.getChannel().sendMessage("<@!"+ping+"> , whatcha pinging me for?"
						+ " Don't make me spam everyones like someone else "
						+ "*cough* ||BUP|| *cough* I know.").queue();
			}else if(command.equalsIgnoreCase(Line4.prefix+"ping")){
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("pong!").queue();
			}else if(command.equalsIgnoreCase(Line4.prefix+"invite")){
				invite.run(event);
			}else if(command.equalsIgnoreCase(Line4.prefix+"setidle")){
				client.getPresence().setStatus(OnlineStatus.IDLE);
			}else if(command.equalsIgnoreCase(Line4.prefix+"setonline")){
				client.getPresence().setStatus(OnlineStatus.ONLINE);
			}else if(command.equalsIgnoreCase(Line4.prefix+"setdnd")
					||command.equalsIgnoreCase(Line4.prefix+"setdonotdisturb")){
				client.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			}else if(command.equalsIgnoreCase(Line4.prefix+"die")
					||command.equalsIgnoreCase(Line4.prefix+"mourir")
					||command.equalsIgnoreCase(Line4.prefix+"sterben")
					||command.equalsIgnoreCase(Line4.prefix+"setdead")
					||command.equalsIgnoreCase(Line4.prefix+"setoffline")){
				client.getPresence().setStatus(OnlineStatus.OFFLINE);
				dead=true;
			}else if(command.equalsIgnoreCase(Line4.prefix+"setstreaming")
					||command.equalsIgnoreCase(Line4.prefix+"setstream")
					||command.equalsIgnoreCase(Line4.prefix+"setplaying")
					||command.equalsIgnoreCase(Line4.prefix+"setgame")
					||command.equalsIgnoreCase(Line4.prefix+"setlistening")
					||command.equalsIgnoreCase(Line4.prefix+"setlisten")
					||command.equalsIgnoreCase(Line4.prefix+"setwatching")
					||command.equalsIgnoreCase(Line4.prefix+"setwatch")
					||command.equalsIgnoreCase(Line4.prefix+"resetstatus")){
				SetStatus.run(event);
			}else if(command.equalsIgnoreCase(Line4.prefix+"say")){
				if(event.getMessage().getContentRaw().length()>4){
					event.getChannel().sendMessage(event.getMessage().getContentRaw().substring(5)).queue();
				}else{
					event.getChannel().sendMessage("Tell me something to say!").queue();
				}
			}else if(command.equalsIgnoreCase(Line4.prefix+"pig")){
				event.getMessage().addReaction("🐷");
			}else if(command.equalsIgnoreCase(Line4.prefix+"pog")){
				event.getMessage().addReaction("749302905374376037");
			}else if(command.equalsIgnoreCase(Line4.prefix+"help")
					||command.equalsIgnoreCase(Line4.prefix+"info")){
				EmbedBuilder b=new EmbedBuilder();
				b.addField("Why do we have *another* bot made by a Fiona wannabe?","Hi there!"
						+ " I'm just another useless bot made because my creator had nothing better to do"
						+ " with his pointless life. I hope I make someone's day a little better!",false);
				b.addField("Basic features","I can't really do anything useful as of now.\n"
						+ "Fun fact: Unlike my buddy Line 3 Scarborough, who was made using Discord.js,"
						+ " I was coded using a Java JDA for Discord.",false);
				b.addField(":upside_down:","Enjoy!",false);
				b.setTitle("Bot info");
				b.setAuthor("Made by Kevin W | aka. Line 5 Eglinton#1345");
				b.setDescription("Another random, useless bot to do more random, useless things!");
				b.setFooter("Requested by "+event.getAuthor().getAsTag());
				b.setColor(purple);
				event.getChannel().sendMessage(b.build()).queue();
			}else if(command.equalsIgnoreCase(Line4.prefix+"transit")){
				try {
					String s="";
					File file=new File("External Files/transitinfo.json");
					Scanner sc=new Scanner(file);
					while(sc.hasNext()){
						s+=sc.nextLine();
					}
					sc.close();
					JSONArray json=new JSONArray(s);
					int num=(int)(Math.random()*json.length());
					JSONObject info=new JSONObject(json.get(num)+"");
					EmbedBuilder b=new EmbedBuilder();
					b.addField("Random facts: ","Model: "+info.getString("model")+"\nTransit agency: "+
							info.getString("company")+"\nBuilt by: "+info.getString("built"),false);
					b.setTitle(info.getString("title"));
					b.setImage(info.getString("img"));
					b.setFooter(event.getAuthor().getAsTag()+" ● "+info.getString("src"));
					b.setColor(purple);
					event.getChannel().sendMessage(b.build()).queue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(command.equalsIgnoreCase(Line4.prefix+"duck")){
				try {
					URL url=new URL("https://random-d.uk/api/quack");
					HttpURLConnection connection= (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuffer response=new StringBuffer();
					String readLine=null;
					while((readLine=in.readLine())!=null){
						response.append(readLine);
					}
					in.close();
					String s=response.toString();
					JSONObject json=new JSONObject(s);
					EmbedBuilder b=new EmbedBuilder();
					b.setTitle("Quack quack!");
					b.setImage(json.getString("url"));
					b.setFooter(event.getAuthor().getAsTag()+" ●  Powered by random-d.uk");
					b.setColor(purple);
					event.getChannel().sendMessage(b.build()).queue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}