package Commands.Moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class SoftBanCommand extends Command {

    public SoftBanCommand() {
        this.name = "softban";
        this.requiredRole = "Bot Tester";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

    }
}
