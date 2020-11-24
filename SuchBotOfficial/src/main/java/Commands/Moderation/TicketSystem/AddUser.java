package Commands.Moderation.TicketSystem;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class AddUser extends Command {

    public AddUser() {
        this.name = "adduser";
        this.help = "Adds a user to the ticket channel the command was sent to. This command requires staff permissions.";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getChannel().getName().contains("-ticket"))   {
            String message = e.getMessage().getContentRaw();
            if (message.split(" ").length == 2) {
                String[] args = message.split(" ");
                IPermissionHolder user1 = e.getGuild().getMemberById(args[1]);
                Member member;
                if (!e.getMessage().getMentionedMembers().isEmpty())    {
                    member = e.getMessage().getMentionedMembers().get(0);
                }
                else    {
                    member = e.getGuild().retrieveMemberById(args[1]).complete();
                }
                if (user1 == null)  {
                    e.getChannel().sendMessage("Couldn't find specified user.").complete();
                }
                TextChannel channel = e.getTextChannel();
                assert user1 != null;
                channel.createPermissionOverride(user1)
                        .setAllow(Permission.MESSAGE_WRITE)
                        .setAllow(Permission.VIEW_CHANNEL)
                        .complete();
                assert member != null;
                e.getChannel().sendMessage("Adding " + member.getAsMention() + " to the channel...").queue();
            }
            else    {
                e.getChannel().sendMessage("You must specify a user").queue();
            }
        }
    }
}
