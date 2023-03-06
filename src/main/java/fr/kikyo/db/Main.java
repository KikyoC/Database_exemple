package fr.kikyo.db;

import fr.kikyo.db.commands.DbCommand;
import fr.kikyo.db.database.MainSQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main extends JavaPlugin {
    private MainSQL mainSQL;


    @Override
    public void onEnable() {
        System.out.println("Test plugin has been enabled");

        setup();
        super.onEnable();
    }

    private void setup(){
        this.mainSQL = new MainSQL();
        this.mainSQL.connect(this);
        registerCommands();
        saveDefaultConfig();
        createTables();
    }

    private void createTables(){
        Connection connection = mainSQL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS test(player VARCHAR(255))");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands(){
        this.getCommand("database").setExecutor(new DbCommand(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void reload(){
        setup();
    }


}
