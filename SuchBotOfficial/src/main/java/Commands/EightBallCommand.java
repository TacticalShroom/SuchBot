package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class EightBallCommand extends Command {

    public EightBallCommand()   {
        this.name = "8ball";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length >= 2)   {
            int rand = new Random().nextInt(100);
            if (rand <= 25)    {
                e.getChannel().sendMessage(e.getMember().getAsMention() + " It's looking like it.").queue();
            }
            else if (rand <= 50)   {
                e.getChannel().sendMessage(e.getMember().getAsMention() + " Odds aren't in your favor.").queue();
            }
            else if (rand <= 75)   {
                e.getChannel().sendMessage(e.getMember().getAsMention() + " maybe...").queue();
            }
            else    {
                e.getChannel().sendMessage(e.getMember().getAsMention() + " How should I know").queue();
            }
        }
    }
}
