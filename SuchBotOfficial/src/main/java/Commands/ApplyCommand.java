package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import javafx.scene.control.Hyperlink;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ApplyCommand extends Command {

    public ApplyCommand()    {
        this.name = "apply";
        this.help = "Sends you the staff application link as well as the requirements to apply.";
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle("Staff Application");
        eb.addField("Requirements", "- Age requirement: 14+\n" +
                "- You need to have at least Young Dragon role\n" +
                "- Need previous Staff experience (With any type of proof, either being a screenshot or a screenshot of a vouch)\n" +
                "- All questions need to be detailed and at least 50-100+ words", true);
        eb.addField("Application Link", "https://forms.gle/Az4deyfTjK6McDoD6", false);
        e.getChannel().sendMessage(eb.build()).complete();
    }
}
