package fr.kikyo.db.commands;

import fr.kikyo.db.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DbCommand implements CommandExecutor {

    private Main main;

    public DbCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            if(strings.equals("reload")){
                main.reload();

            }
        }
        return false;
    }
}
