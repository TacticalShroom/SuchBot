package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.Objects;

public class BanCommand extends Command {

    public BanCommand() {
        this.name = "ban";
        this.help = "Bans the specified member. This command requires staff permissions";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.BAN_MEMBERS))   {
            String message = e.getMessage().getContentRaw();
            if (message.split(" ").length >= 2) {
                String[] args = message.split(" ");
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
                    e.getGuild().ban(member, 999999999).queue();
                    e.getChannel().sendMessage("Successfully Banned " + member.getAsMention() + ".").queue();
                }
            }
        }
    }
}
