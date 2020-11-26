package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class SoftBanCommand extends Command {

    public SoftBanCommand() {
        this.name = "softban";
        this.requiredRole = "Bot Tester";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length >= 2)   {
            if (e.getGuild().retrieveMemberById(e.getAuthor().getIdLong()).complete().hasPermission(Permission.BAN_MEMBERS))   {
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
                    e.getGuild().ban(member, 999999999).complete();
                    e.getGuild().unban(member.getUser()).complete();
                    e.getChannel().sendMessage("Successfully soft banned " + member.getAsMention()).queue();
                }
            }
        }
    }
}
