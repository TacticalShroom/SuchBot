package Commands.Moderation.WarnSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UnWarnCommand extends Command {

    public UnWarnCommand()  {
        this.name = "unwarn";
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
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Warns.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Warns.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                String warnID = args[2];
                String reason = c.getString("warns.warn-" + warnID);
                c.remove("warns.warn-" + warnID);
                String time = c.getString("warns.warn-" + warnID + "time");
                c.remove("warns.warn-" + warnID + "time");
                int warns = c.getInt("warncount");
                while (warns > 0)   {
                    String warnIDTest = c.getString("warns.warn-" + warns);
                    if (warnIDTest.equals(warnID))  {
                        c.remove("warns.warn-" + warns);
                    }
                    warns -= 1;
                }
                int warnings = c.getInt("warncount");
                warnings -= 1;
                c.set("warncount", warnings);

                try {
                    c.save(f);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.RED);
                eb.setTitle("Successfully Removed warn");
                eb.addField("Warn Details", "Reason: " + reason + "\nDate: " + time, false);
                eb.setThumbnail(member.getUser().getAvatarUrl());
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }
}
