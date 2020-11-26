package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SocialsCommand extends Command {

    public SocialsCommand() {
        this.name = "socials";
    }

    @Override
    protected void execute(CommandEvent e) {
        TextChannel channel = e.getTextChannel();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("SuchSpeed's Socials");
        eb.setColor(Color.RED);
        eb.setDescription("**Visit all of SuchSpeed's Socials!**" + "\n**YouTube**:     https://www.youtube.com/user/SuchSpeed" + "\n**Twitter**:     https://twitter.com/SuchSpeed" +
                "\n**Instagram**:     https://www.instagram.com/tem/" +
                "\n**Twitch**:     http://www.twitch.tv/SuchSpeed" +
                "\n**Merch**:     https://suchspeed.merchforall.com/" +
                "\n**Discord**:     https://discord.gg/SuchSpeed"
                );
        channel.sendMessage(eb.build()).queue();
    }
}
