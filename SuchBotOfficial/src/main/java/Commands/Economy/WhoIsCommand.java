package Commands.Economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class WhoIsCommand extends Command {

    public WhoIsCommand()   {
        this.name = "whois";
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
                try {
                        new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                int balance = c.getInt("economy.balance");
                c.set("economy.balance", balance);

                String month = member.getTimeJoined().getMonth().toString().toLowerCase();
                int day = member.getTimeJoined().getDayOfMonth();
                int year = member.getTimeJoined().getYear();
                int hour = member.getTimeJoined().getHour();
                int minute = member.getTimeJoined().getMinute();
                int second = member.getTimeJoined().getSecond();
                String time = month + " " + day + ", " + year + " " + hour + ":" + minute + ":" + second;

                String month1 = member.getTimeCreated().getMonth().toString().toLowerCase();
                int day1 = member.getTimeCreated().getDayOfMonth();
                int year1 = member.getTimeCreated().getYear();
                String timeCreated = month1 + " " + day1 + ", " + year1;

                int level = c.getInt("leveling.level");
                int xp = c.getInt("leveling.xp");
                String progressBar = "null";
                c.set("leveling.level", level);
                c.set("leveling.xp", xp);
                String color = c.getString("stats.color");
                if (color == null)  {
                    c.set("stats.color", "red");
                    color = "red";
                }
                if (color.equals("red")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\u2B1C";
                    }
                }
                else if (color.equals("green")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\u2B1C";
                    }
                }
                else if (color.equals("brown")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\u2B1C";
                    }
                }
                else if (color.equals("purple")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\uD83D\uDFEA\u2B1C";
                    }
                }
                else if (color.equals("blue")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\uD83D\uDFE6\u2B1C";
                    }
                }
                else if (color.equals("black")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1B\u2B1C";
                    }
                }
                else if (color.equals("gold")) {
                    if (xp < 13)    {
                        progressBar = "\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 26)    {
                        progressBar = "\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 40)    {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 53)    {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 66)    {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 80)    {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 93)    {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 106)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 120)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 133)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 146)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 160)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 173)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C\u2B1C";
                    }
                    else if (xp < 186)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C\u2B1C";
                    }
                    else if (xp <= 200)  {
                        progressBar = "\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\u2B1C";
                    }
                }

                Object[] roles = member.getRoles().toArray();

                ArrayList<String> roles1 = new ArrayList<>();
                for (Object role : roles) {
                    if (role instanceof Role)   {
                        Role roleToAdd = (Role) role;
                        roles1.add(roleToAdd.getAsMention());
                    }
                }
                String memberRoles = roles1.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "\n");

                EmbedBuilder eb = new EmbedBuilder();

                if (color.equals("red"))    {
                    eb.setColor(Color.RED);
                }
                else if (color.equals("green")) {
                    eb.setColor(Color.GREEN);
                }
                else if (color.equals("brown")) {
                    eb.setColor(Color.getHSBColor(0, (float) 74.55, (float) 64.71));
                }
                else if (color.equals("purple"))    {
                    eb.setColor(Color.HSBtoRGB(266, 75, 85));
                }
                else if (color.equals("blue"))  {
                    eb.setColor(Color.BLUE);
                }
                else if (color.equals("black")) {
                    eb.setColor(Color.BLACK);
                }
                else if (color.equals("gold"))  {
                    eb.setColor(Color.ORANGE);
                }

                eb.setImage(member.getUser().getAvatarUrl());
                eb.setTitle(member.getUser().getName() + "'s Info");
                eb.addField("Roles", memberRoles, false);
                eb.addField("Stats", "Joined Server: " + time + "\nAccount Created: " + timeCreated + "               \nTokens: " + balance, true);
                eb.addField("Experience", "**Level**: " + level + "\nExp: " + xp + " xp", false);
                eb.addField("Level Progress", progressBar, false);
                eb.setDescription("Gain experience by sending messages and \nbeing active in the community.");
                e.getChannel().sendMessage(eb.build()).queue();

                try {
                    c.save(f);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}