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
import java.util.ArrayList;

public class WarningsCommand extends Command {

    public WarningsCommand()    {
        this.name = "warnings";
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
                EmbedBuilder eb = new EmbedBuilder();
                try {
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Warns.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Warns.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                int warns = c.getInt("warncount");
                ArrayList<String> warnIDs = new ArrayList<>();
                while (warns > 0)   {
                    warnIDs.add(c.getString("warns.warn-" + warns));
                    warns -= 1;
                }
                warns += 1;
                for (String warnID : warnIDs)   {
                    String time = c.getString("warns.warn-" + warnID + "time");
                    String reason = c.getString("warns.warn-" + warnID);
                    eb.addField("Warn: " + warns, "Reason: " + reason + "\nDate: " + time + "\nWarning ID: " + warnID, false);
                    warns +=1;
                }
                if (c.getInt("warncount") == 0) {
                    eb.setDescription(member.getUser().getName() + " has no warnings.");
                }
                eb.setColor(Color.RED);
                eb.setTitle(member.getUser().getName() + "'s Warnings");
                eb.setThumbnail(member.getUser().getAvatarUrl());
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }
}
