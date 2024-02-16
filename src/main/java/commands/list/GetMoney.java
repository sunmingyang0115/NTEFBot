package commands.list;


import frank.Run;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;


import java.io.IOException;
import db.Database;
import commands.CommandManager;
import commands.OptionDataManager;

import java.sql.SQLException;

public class GetMoney extends CommandManager {


    @Override
    public String name() {
        return "balance";
    }

    @Override
    public String description() {
        return "shid coin money";
    }

    @Override
    public OptionDataManager[] options() {
        return new OptionDataManager[0];
    }

    @Override
    public void execute(SlashCommandEvent event) throws IOException, SQLException {
        event.reply("you have " + Run.db.getItemQuantity(Database.getSQID(event.getUser().getId()), Database.conjugationCurrency) + " <:shidcoin:####>").queue();
    }
}
