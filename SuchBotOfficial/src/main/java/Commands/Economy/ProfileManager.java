package Commands.Economy;

import net.dv8tion.jda.api.entities.Member;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ProfileManager {

    public boolean hasGreen(Member member)   {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("green") || color.equals("brown") || color.equals("purple") || color.equals("blue") || color.equals("black") || color.equals("gold"))  {
            return true;
        }
        return false;
    }

    public boolean hasBrown(Member member)  {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("brown") || color.equals("purple") || color.equals("blue") || color.equals("black") || color.equals("gold"))  {
            return true;
        }
        return false;
    }

    public boolean hasPurple(Member member) {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("purple") || color.equals("blue") || color.equals("black") || color.equals("gold"))  {
            return true;
        }
        return false;
    }

    public boolean hasBlue(Member member)   {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("blue") || color.equals("black") || color.equals("gold"))  {
            return true;
        }
        return false;
    }

    public boolean hasBlack(Member member)  {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("black") || color.equals("gold"))  {
            return true;
        }
        return false;
    }

    public boolean hasGold(Member member)   {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        String color = c.getString("stats.color");
        if (color == null)  {
            c.set("stats.color", "red");
            return false;
        }
        if (color.equals("gold"))  {
            return true;
        }
        return false;
    }

}
