package Commands.Moderation.StrikeSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class StrikeCommand extends Command {

    public StrikeCommand()  {
        this.name = "strike";
        this.requiredRole = "Bot Tester";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length >= 2)   {
            Member member;
            if (!e.getMessage().getMentionedMembers().isEmpty())    {
                member = e.getMessage().getMentionedMembers().get(0);
            }
            else    {
                member = e.getGuild().retrieveMemberById(args[1]).complete();
            }
            if (member == null) {
                e.getChannel().sendMessage("Could not find specified user ID.").queue();
            }
            else    {
                if (member.isOwner()) {
                    e.getChannel().sendMessage("You can not strike the owner!").queue();
                }
                else if (member.hasPermission(Permission.MESSAGE_MANAGE))    {
                    try {
                        new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sStrikes.yml").createNewFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sStrikes.yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    String strikeID = e.getMessage().getId().replaceAll("`", "");
                    String reason = Arrays.toString(args)
                            .trim()
                            .replaceAll(",", "")
                            .replace("s!strike ", "")
                            .replace(args[1] + " ", "")
                            .replace("[", "")
                            .replace("]", "");
                    if (args.length == 2)    {
                        reason = "No reason specified.";
                    }
                    c.set("strikes.strike-" + strikeID, reason);
                    int strikes = c.getInt("strikecount");
                    if (strikes >= 0) {
                        strikes += 1;
                    } else {
                        strikes = 1;
                    }
                    c.set("strikes.strike-" + strikes, strikeID);
                    c.set("strikecount", strikes);
                    int day = e.getMessage().getTimeCreated().getDayOfMonth();
                    int year = e.getMessage().getTimeCreated().getYear();
                    String month = e.getMessage().getTimeCreated().getMonth().toString().toLowerCase();
                    int hour = e.getMessage().getTimeCreated().getHour();
                    int minute = e.getMessage().getTimeCreated().getMinute();
                    int second = e.getMessage().getTimeCreated().getSecond();
                    String time = month + " " + day + ", " + year + " " + hour + ":" + minute + ":" + second;
                    c.set("strikes.strike-" + strikeID + "time", time);
                    try {
                        c.save(f);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setColor(Color.RED);
                    eb.setThumbnail(member.getUser().getAvatarUrl());
                    eb.setTitle("Successfully strikeed " + member.getUser().getName());
                    eb.addField("Reason", reason, true);
                    eb.addField("Details", "strike ID: " + strikeID + "\nTotal strikings: " + strikes + "\nDate: " + time, false);
                    eb.setFooter("Do s!strikings <userID> to view all strikes a member has.");
                    e.getChannel().sendMessage(eb.build()).queue();
                }
                else    {
                    e.getChannel().sendMessage("You can only strike staff members!").queue();
                }
            }
        }
        else    {
            e.getChannel().sendMessage("Incorrect usage! Try ```s!strike <userID> (reason)```").queue();
        }
    }
}
