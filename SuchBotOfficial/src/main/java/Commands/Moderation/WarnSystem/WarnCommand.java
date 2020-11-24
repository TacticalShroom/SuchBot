package Commands.Moderation.WarnSystem;
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

public class WarnCommand extends Command {

    public WarnCommand()  {
        this.name = "warn";
        this.help = "Warns the specified user";
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
                if (member.hasPermission(Permission.ADMINISTRATOR)) {
                    e.getChannel().sendMessage("You can not warn an administrator!").queue();
                }
                else    {
                    try {
                        new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sWarns.yml").createNewFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sWarns.yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    String warnID = e.getMessage().getId().replaceAll("`", "");
                    String reason = Arrays.toString(args)
                            .trim()
                            .replaceAll(",", "")
                            .replace("s!warn ", "")
                            .replace(args[1] + " ", "")
                            .replace("[", "")
                            .replace("]", "");
                    if (args.length == 2)    {
                        reason = "No reason specified.";
                    }
                    c.set("warns.warn-" + warnID, reason);
                    int warns = c.getInt("warncount");
                    if (warns >= 0) {
                        warns += 1;
                    } else {
                        warns = 1;
                    }
                    c.set("warns.warn-" + warns, warnID);
                    c.set("warncount", warns);
                    int day = e.getMessage().getTimeCreated().getDayOfMonth();
                    int year = e.getMessage().getTimeCreated().getYear();
                    String month = e.getMessage().getTimeCreated().getMonth().toString().toLowerCase();
                    int hour = e.getMessage().getTimeCreated().getHour();
                    int minute = e.getMessage().getTimeCreated().getMinute();
                    int second = e.getMessage().getTimeCreated().getSecond();
                    String time = month + " " + day + ", " + year + " " + hour + ":" + minute + ":" + second;
                    c.set("warns.warn-" + warnID + "time", time);
                    try {
                        c.save(f);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setColor(Color.RED);
                    eb.setThumbnail(member.getUser().getAvatarUrl());
                    eb.setTitle("Successfully Warned " + member.getUser().getName());
                    eb.addField("Reason", reason, true);
                    eb.addField("Details", "Warn ID: " + warnID + "\nTotal Warnings: " + warns + "\nDate: " + time, false);
                    eb.setFooter("Do s!warnings <userID> to view all warns a member has.");
                    e.getChannel().sendMessage(eb.build()).queue();
                }
            }
        }
        else    {
            e.getChannel().sendMessage("Incorrect usage! Try ```s!warn <userID> (reason)```").queue();
        }
    }
}
