package Commands.Moderation.TicketSystem;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogListener extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String author = e.getAuthor().getName();
        String message = e.getMessage().getContentRaw();
        if (e.getChannel().getName().contains("-ticket"))   {
            if (!e.getMessage().getAuthor().isBot()) {
                try {
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + e.getChannel().getName() + "Logs.txt").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\titan\\SuchBotFiles\\" + e.getChannel().getName() + "Logs.txt", true))) {
                    writer.print(author + ": " + message + "\n");
                } catch (IOException ex) {
                    // ... handle IO exception
                }
            }
        }
    }
}
