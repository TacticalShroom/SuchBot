package Commands.Economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ShopCommand extends Command {

    public ShopCommand()    {
        this.name = "shop";
    }

    @Override
    protected void execute(CommandEvent e) {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + "ID" + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + "ID" + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Token Shop");
        eb.setColor(Color.RED);
        eb.setDescription("Get tokens to purchase items from the shop \nby leveling up and gambling.");
        eb.addField("Profile Colors", "**1** - Green :green_square: - 200 tokens (Requires Red) \n" +
                "**2** - Brown :brown_square: - 400 tokens (Requires Green) \n" +
                "**3** - Purple :purple_square: - 600 tokens (Requires Brown) \n" +
                "**4** - Blue :blue_square: - 1000 tokens (Requires Purple) \n" +
                "**5** - Black :black_large_square: - 1500 tokens (Requires Blue) \n" +
                "**6** - Gold :orange_square: - 2000 tokens (Requires Black)", false);
        e.getChannel().sendMessage(eb.build()).queue(message -> {
            message.addReaction(EmojiParser.parseToUnicode(":one:")).queue();
            message.addReaction(EmojiParser.parseToUnicode(":two:")).queue();
            message.addReaction(EmojiParser.parseToUnicode(":three:")).queue();
            message.addReaction(EmojiParser.parseToUnicode(":four:")).queue();
            message.addReaction(EmojiParser.parseToUnicode(":five:")).queue();
            message.addReaction(EmojiParser.parseToUnicode(":six:")).queue();
            c.set("Ids.shop", message.getId());
            try {
                c.save(f);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}
