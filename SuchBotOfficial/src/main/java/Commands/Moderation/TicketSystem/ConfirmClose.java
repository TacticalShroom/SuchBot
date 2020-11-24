package Commands.Moderation.TicketSystem;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.AttachmentOption;
import org.jetbrains.annotations.NotNull;

import java.io.*;


public class ConfirmClose extends ListenerAdapter {

    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent e) {
        if (e.getChannel().getName().equals(e.getUser().getName().toLowerCase() + "s-ticket")) {
            if (e.getReaction().getReactionEmote().getEmoji().equals("\u2705")) {
                e.getReaction().removeReaction(e.getUser()).complete();
                TextChannel log = e.getGuild().getTextChannelById(778704920190517268L);
                assert log != null;
                log.sendFile(new File("C:\\Users\\titan\\SuchBotFiles\\" + e.getChannel().getName() + "Logs.txt"), AttachmentOption.SPOILER).queue();
                e.getChannel().delete().complete();
                new File("C:\\Users\\titan\\SuchBotFiles\\" + e.getChannel().getName() + "Logs.txt").delete();
            }
        }
    }
}
