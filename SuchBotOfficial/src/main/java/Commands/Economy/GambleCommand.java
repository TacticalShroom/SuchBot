package Commands.Economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GambleCommand extends Command {

    public GambleCommand()  {
        this.name = "gamble";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length == 2)   {
            int amount = Integer.parseInt(args[1]);
            if (amount >= 50)    {
                Member member = e.getMember();
                try {
                    new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                int balance = c.getInt("economy.balance");
                if (balance >= amount)  {
                    Random r = new Random();
                    if (r.nextInt(100) <= 40)   {
                        balance += amount;
                        c.set("economy.balance", balance);
                        e.getChannel().sendMessage("Congratulations " + member.getAsMention() + "! You have won " + amount + " tokens!").queue();
                    }
                    else    {
                        balance -= amount;
                        c.set("economy.balance", balance);
                        e.getChannel().sendMessage("Sorry " + member.getAsMention() + ", but you have lost " + amount + " tokens.").queue();
                    }
                    try {
                        c.save(f);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                else    {
                    e.getChannel().sendMessage("You do not have enough tokens to do this command.").queue();
                }
            }
            else {
                e.getChannel().sendMessage(e.getAuthor().getAsTag() + " You must gamble at least 50 tokens!").queue();
            }
        }
    }
}
