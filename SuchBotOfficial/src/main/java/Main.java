import Commands.Economy.*;
import Commands.EightBallCommand;
import Commands.Moderation.*;
import Commands.ApplyCommand;
import Commands.Moderation.StrikeSystem.StrikeCommand;
import Commands.Moderation.StrikeSystem.StrikesCommand;
import Commands.Moderation.StrikeSystem.UnStrikeCommand;
import Commands.Moderation.TicketSystem.*;
import Commands.Moderation.WarnSystem.UnWarnCommand;
import Commands.Moderation.WarnSystem.WarnCommand;
import Commands.Moderation.WarnSystem.WarningsCommand;
import Commands.SocialsCommand;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws LoginException, SQLException {
        JDA jda = JDABuilder.createDefault("NzI1NDIzMjQ5MDQyNzAyNDcy.XvOhDA.OstZtqRC0xtYU0zeM5omftgY5Vw")
                .build();

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix("s!");
        builder.setOwnerId("725423249042702472");
        builder.setActivity(Activity.playing("Under Development"));
        builder.addCommand(new KickCommand());
        builder.addCommand(new MuteCommand());
        builder.addCommand(new BanCommand());
        builder.addCommand(new AddUser());
        builder.addCommand(new CloseTicket());
        builder.addCommand(new TicketSetup2());
        builder.addCommand(new ApplyCommand());
        builder.addCommand(new WarnCommand());
        builder.addCommand(new WarningsCommand());
        builder.addCommand(new UnWarnCommand());
        builder.addCommand(new StrikeCommand());
        builder.addCommand(new StrikesCommand());
        builder.addCommand(new UnStrikeCommand());
        builder.addCommand(new EightBallCommand());
        builder.addCommand(new SoftBanCommand());
        builder.addCommand(new WhoIsCommand());
        builder.addCommand(new PurgeCommand());
        builder.addCommand(new SocialsCommand());
        builder.addCommand(new GambleCommand());
        builder.addCommand(new ShopCommand());

        CommandClient client = builder.build();
        jda.addEventListener(client);

        jda.addEventListener(new ReactionTicket());
        jda.addEventListener(new ConfirmClose());
        jda.addEventListener(new LogListener());
        jda.addEventListener(new ExpSystemManager());
        jda.addEventListener(new ShopListener());

        ExpSystemManager systemManager = new ExpSystemManager();
        systemManager.startTimer();
    }

}
