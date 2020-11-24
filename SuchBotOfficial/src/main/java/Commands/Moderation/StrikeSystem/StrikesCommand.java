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
import java.util.ArrayList;

public class StrikesCommand extends Command {

    public StrikesCommand() {
        this.name = "strikings";
        this.requiredRole = "staff";
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
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sStrikes.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getUser().getName() + "sStrikes.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                int strikes = c.getInt("strikecount");
                ArrayList<String> strikeIDs = new ArrayList<>();
                while (strikes > 0)   {
                    strikeIDs.add(c.getString("strikes.strike-" + strikes));
                    strikes -= 1;
                }
                strikes += 1;
                for (String strikeID : strikeIDs)   {
                    String time = c.getString("strikes.strike-" + strikeID + "time");
                    String reason = c.getString("strikes.strike-" + strikeID);
                    eb.addField("strike: " + strikes, "Reason: " + reason + "\nDate: " + time + "\nstriking ID: " + strikeID, false);
                    strikes +=1;
                }
                if (c.getInt("strikecount") == 0) {
                    eb.setDescription(member.getUser().getName() + " has no strikings.");
                }
                eb.setColor(Color.RED);
                eb.setTitle(member.getUser().getName() + "'s strikings");
                eb.setThumbnail(member.getUser().getAvatarUrl());
                e.getChannel().sendMessage(eb.build()).queue();
            }
        }
    }
}
