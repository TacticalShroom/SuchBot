package Commands.Moderation.TicketSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class CloseTicket extends Command {

    public CloseTicket()   {
        this.name = "close";
        this.help = "Closes your opened ticket.";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getChannel().getName().equals(e.getAuthor().getName().toLowerCase() + "s-ticket")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription("Are you sure you want to close this ticket, \nyou will not be able to view these messages again. \nReact with the ✅ to confirm.");

            e.getChannel().sendMessage(eb.build())
                    .complete()
                    .addReaction("U+2705").complete();
        }
    }
}
