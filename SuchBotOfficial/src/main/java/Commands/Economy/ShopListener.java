package Commands.Economy;

import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ShopListener extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        if (e.getUser().isBot())    return;
        ShopCommand shop = new ShopCommand();
        ProfileManager manager = new ProfileManager();
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + "ID" + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f1 = new File("C:\\Users\\titan\\SuchBotFiles\\" + "ID" + "-Info.yml");
        FileConfiguration c1 = YamlConfiguration.loadConfiguration(f1);
        if (c1.getString("Ids.shop").equals(e.getMessageId()))   {
            Member member = e.getMember();
            e.getReaction().removeReaction(member.getUser()).queue();
            try {
                new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            int balance = c.getInt("economy.balance");
            if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":one:")))  {
                if (balance >= 200 && !manager.hasGreen(member))    {
                    c.set("stats.color", "green");
                    balance -= 200;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color green!").queue();
                }
            }
            else if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":two:"))) {
                if (balance >= 400 && manager.hasGreen(member) && !manager.hasBrown(member))  {
                    c.set("stats.color", "brown");
                    balance -= 400;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color brown!").queue();
                }
            }
            else if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":three:")))   {
                if (balance >= 600 && manager.hasBrown(member) && !manager.hasPurple(member))   {
                    c.set("stats.color", "purple");
                    balance -= 600;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color purple!").queue();
                }
            }
            else if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":four:")))    {
                if (balance >= 1000 && manager.hasPurple(member) && !manager.hasBlue(member))   {
                    c.set("stats.color", "blue");
                    balance -= 1000;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color blue!").queue();
                }
            }
            else if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":five:")))    {
                if (balance >= 1500 && manager.hasBlue(member) && !manager.hasBlack(member))    {
                    c.set("stats.color", "black");
                    balance -= 1500;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color black!").queue();
                }
            }
            else if (e.getReaction().getReactionEmote().getEmoji().equals(EmojiParser.parseToUnicode(":six:")))    {
                if (balance >= 2000 && manager.hasBlack(member) && !manager.hasGold(member))    {
                    c.set("stats.color", "gold");
                    balance -= 2000;
                    e.getChannel().sendMessage(member.getAsMention() + " has bought profile color gold!").queue();
                }
            }
            c.set("economy.balance", balance);
            try {
                c.save(f);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
