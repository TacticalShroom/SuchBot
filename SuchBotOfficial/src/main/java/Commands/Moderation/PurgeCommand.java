package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.TextChannel;

public class PurgeCommand extends Command {

    public PurgeCommand()   {
        this.name = "purge";
        this.requiredRole = "Bot Tester";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length == 2)   {
            TextChannel channel = e.getTextChannel();
            int messagesToDelete = Integer.parseInt(args[1]);
            if (messagesToDelete >= 0)  {
                while (messagesToDelete > 0)    {
                    String messageID = channel.getLatestMessageId();
                    channel.deleteMessageById(messageID).complete();
                    messagesToDelete -= 1;
                }
                e.replyInDm("You have purged **" + args[1] + "** messages.");
            }
        }
    }
}
