package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.Objects;

public class KickCommand extends Command {

    public KickCommand()    {
        this.name = "kick";
        this.help = "Kicks the specified member. This command requires staff permissions";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        if (Objects.requireNonNull(commandEvent.getMember()).hasPermission(Permission.KICK_MEMBERS))   {
            String message = commandEvent.getMessage().getContentRaw();
            if (message.split(" ").length >= 2) {
                String[] args = message.split(" ");
                Member member;
                if (!commandEvent.getMessage().getMentionedMembers().isEmpty())    {
                    member = commandEvent.getMessage().getMentionedMembers().get(0);
                }
                else    {
                    member = commandEvent.getGuild().retrieveMemberById(args[1]).complete();
                }
                if (member == null) {
                    commandEvent.getChannel().sendMessage("Could not find specified user ID.").queue();
                }
                else    {
                    member.kick().queue();
                    commandEvent.getChannel().sendMessage("Successfully Kicked " + member.getAsMention() + ".").queue();
                }
            }
        }
    }
}
