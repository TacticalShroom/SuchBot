package Commands.Moderation.TicketSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;

public class TicketSetup2 extends Command {

    public TicketSetup2()   {
        this.name = "ticket setup";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getMember().hasPermission(Permission.ADMINISTRATOR))  {
            e.getMessage().delete().queue();
            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.white);
            eb.setTitle("General Support");
            eb.addField("Create a Ticket", "To create a ticket, react with \uD83C\uDF9F", true);
            e.getChannel().sendMessage(eb.build()).complete().addReaction("\uD83C\uDF9F").complete();
        }
    }
}
