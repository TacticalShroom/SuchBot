package Commands.Economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExpSystemManager extends ListenerAdapter {

    HashMap<Member, Integer> playerTimer = new HashMap<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Member member = e.getMember();
        assert member != null;
        if (canGetExp(member))   {
            try {
                randXP(member);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            setPlayerTimer(member, 1);
            if (canLevelUp(member)) {
                try {
                    PlayerLevelUp(member);
                    e.getChannel().sendMessage( "Congratulations " + member.getAsMention() + " you have leveled up to level " + getLevel(member) + " and have been awarded with " + getLevel(member)*50 + "tokens!").queue();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        if (e.getMessage().getContentRaw().equals("s!xp"))  {
            try {
                e.getChannel().sendMessage("You have " + getExp(Objects.requireNonNull(member))).queue();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public int getExp(Member member) throws IOException {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        int Exp;
        if (c.get("leveling.xp") == null)   {
            Exp = 0;
            c.set("leveling.xp", Exp);
            c.save(f);
        }
        else    {
            Exp = c.getInt("leveling.xp");
        }
        return Exp;
    }

    public void PlayerLevelUp(Member member) throws IOException {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        int level = c.getInt("leveling.level");
        level += 1;
        c.set("leveling.level", level);
        int Exp = c.getInt("leveling.xp");
        Exp -= 200;
        int tokens = c.getInt("economy.balance");
        tokens += level*50;
        c.set("economy.balance", tokens);
        c.set("leveling.xp", Exp);
        c.save(f);
    }

    public int getLevel(Member member)  {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        return c.getInt("leveling.level");
    }

    public boolean canLevelUp(Member member)    {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        int Exp = c.getInt("leveling.xp");
        if (Exp >= 200) {
            return true;
        }
        return false;
    }

    public void addExp(Member member, int xp) throws IOException {
        try {
            new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml").createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File f = new File("C:\\Users\\titan\\SuchBotFiles\\" + member.getId() + "-Info.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        int Exp;
        if (c.get("leveling.xp") == null)   {
            Exp = 0;
        }
        else    {
            Exp = c.getInt("leveling.xp");
        }
        Exp += xp;
        c.set("leveling.xp", Exp);
        c.save(f);
    }

    public int getPlayerTime(Member member) {
        return playerTimer.get(member);
    }

    public void setPlayerTimer(Member member, int time) {
        playerTimer.put(member, time);
    }

    public void randXP(Member member) throws IOException {
        Random r = new Random();
        addExp(member, r.nextInt(5));
    }

    public boolean canGetExp(Member member) {
        if (!playerTimer.containsKey(member))   {
            return true;
        }
        return false;
    }

    public void startTimer()    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (Member member : playerTimer.keySet())  {
                    if (getPlayerTime(member) > 0)  {
                        setPlayerTimer(member, getPlayerTime(member) - 1);
                    }
                    else if (getPlayerTime(member) == 0)    {
                        playerTimer.remove(member);
                    }
                }
            }
        };
        timer.schedule(task, 1000, 1000);
    }

}
