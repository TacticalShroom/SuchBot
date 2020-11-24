package Commands.Moderation.TicketSystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public class ReactionTicket extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent e) {
        User reactor = e.getUser();
        Guild server = e.getGuild();
        Category modSupport = server.getCategoryById(778375088650190868L);
        IPermissionHolder user = e.getMember();
        if (!e.getUser().isBot())    {
            if (e.getReaction().getReactionEmote().getEmoji().equals("\uD83C\uDF9F"))  {
                if (e.getChannel().getName().equals("create-a-ticket-test"))  {
                    e.getReaction().removeReaction(reactor).queue();
                    assert modSupport != null;
                    modSupport.createTextChannel(reactor.getName().toLowerCase() + "s-ticket")
                            .addPermissionOverride(user, EnumSet.of(Permission.MESSAGE_WRITE), null)
                            .addPermissionOverride(user, EnumSet.of(Permission.VIEW_CHANNEL), null)
                            .addRolePermissionOverride(778361104643653683L, EnumSet.of(Permission.MESSAGE_WRITE), null)
                            .addRolePermissionOverride(778361104643653683L, EnumSet.of(Permission.VIEW_CHANNEL), null)
                            .complete();
                    List<TextChannel> modChannels = modSupport.getTextChannels();

                    for (TextChannel channel : modChannels)    {
                        if (channel.getName().equals(reactor.getName().toLowerCase() + "s-ticket"))   {
                            String month = channel.getTimeCreated().getMonth().toString().toLowerCase();
                            int day = channel.getTimeCreated().getDayOfMonth();
                            int hour = channel.getTimeCreated().getHour();
                            int minute = channel.getTimeCreated().getMinute();
                            int second = channel.getTimeCreated().getSecond();
                            String time = month + " " + day + ", " + hour + ":" + minute + ":" + second;
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setTitle("Support Ticket");
                            eb.setColor(Color.WHITE);
                            eb.addField(reactor.getName() + "'s Support Ticket", "Created: " + time + "\nA staff member will be with you shortly...", true);
                            eb.setFooter("Do `s!close` to close the ticket.");
                            channel.sendMessage(eb.build()).queue();
                        }
                    }
                }
            }
        }
    }
}
