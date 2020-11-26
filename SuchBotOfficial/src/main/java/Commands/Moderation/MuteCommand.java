package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.Objects;

public class MuteCommand extends Command {

    public MuteCommand()    {
        this.name = "mute";
        this.help = "Mutes specified member, repeat to unmute member. This command requires staff permissions";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.VOICE_MUTE_OTHERS))  {
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
                Role role = e.getGuild().getRoleById(672550127935684618L);
                if (member == null) {
                    e.getChannel().sendMessage("Could not find specified user ID.").queue();
                }
                else    {
                    if (!member.getRoles().contains(role))  {
                        assert role != null;
                        e.getGuild().addRoleToMember(member, role).queue();
                        e.getChannel().sendMessage("Successfully Muted " + member.getAsMention() + ".").queue();
                    }
                    else    {
                        assert role != null;
                        e.getGuild().removeRoleFromMember(member, role).queue();
                        e.getChannel().sendMessage("Successfully Unmuted " + member.getAsMention() + ".").queue();
                    }
                }
            }
        }
    }
}