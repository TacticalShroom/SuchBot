package Commands.Moderation.StrikeSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UnStrikeCommand extends Command {

    public UnStrikeCommand()    {
        this.name = "unstrike";
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
                try {
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Strikes.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Strikes.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                String strikeID = args[2];
                String reason = c.getString("strikes.strike-" + strikeID);
                c.remove("strikes.strike-" + strikeID);
                String time = c.getString("strikes.strike-" + strikeID + "time");
                c.remove("strikes.strike-" + strikeID + "time");
                int strikes = c.getInt("strikecount");
                while (strikes > 0)   {
                    String strikeIDTest = c.getString("strikes.strike-" + strikes);
                    if (strikeIDTest.equals(strikeID))  {
                        c.remove("strikes.strike-" + strikes);
                    }
                    strikes -= 1;
                }
                int strikeings = c.getInt("strikecount");
                strikeings -= 1;
                c.set("strikecount", strikeings);

                try {
                    c.save(f);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.RED);
                eb.setTitle("Successfully Removed strike");
                eb.addField("strike Details", "Reason: " + reason + "\nDate: " + time, false);
                eb.setThumbnail(member.getUser().getAvatarUrl());
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }
}
