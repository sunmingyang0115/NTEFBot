package frank;

import commands.CommandManager;
import commands.list.*;
import commands.OptionDataManager;
import commands.list.Hlfrench;
import commands.list.hlfrench.Tester;
import db.Database;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

public class Run {
    //bottoken
    private static String token;

    public static Swears swear;

    //guild id
    private static String guildid = "####";
    public static String channelid = "####";
    public static String ownerid = "####";
    public static String[] pinEmotes = {"\uD83D\uDCCC","\uD83D\uDCCD","\uD83E\uDDF7"};

    public static HashMap<String, CommandManager> commandManagerTreeMap = new HashMap<>();

    //telescreen
    public static HashMap<String, Integer> telescreenTreeMap = new HashMap<>();
    public static String[] response = {"we are the dead", "it was behind the picture", "now they can see us", "the house is surrounded", "i suppose we may as well say goodbye"};
    public static Database db;
    public static JDA jda;
    
    public static JDA getJDA() {
        return jda;
    }

    public static void main(String args[]) throws LoginException, InterruptedException, IOException, SQLException {
        //so it doesnt error on start up
        BasicConfigurator.configure();

        token = read("token.txt");

        swear = new Swears("swears.txt");


        db = new Database();


        jda = JDABuilder.create(token, EnumSet.allOf(GatewayIntent.class))
                .addEventListeners(new EventManager())
                .setActivity(Activity.playing("amogOS"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
        

        jda.awaitReady();

        CommandListUpdateAction commands = jda.getGuildById(guildid).updateCommands();
        CommandManager[] commandManager = {new FindUser(), new SocialCredit(), new Translate(), new KD(), new Duedate(), new Hlfrench(), new GetMoney()};
        for (CommandManager cm : commandManager) {
            commandManagerTreeMap.put(cm.name(), cm);
            CommandData cd = new CommandData(cm.name(),cm.description());
            for (OptionDataManager odm : cm.options()) {
                cd.addOptions(new OptionData(odm.type(), odm.name(), odm.description()).setRequired(odm.isRequired()));
            }
            commands.addCommands(cd);
        }
        commands.queue();


//        new ConsolePanel();


//       createConsole();

       //hl french
        new Tester();

        //


    }

    private static String read(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
       return br.readLine();
    }


//
//    private static void createConsole() {
//
//    }


}

// Among us Sussy Baka
// thanks
