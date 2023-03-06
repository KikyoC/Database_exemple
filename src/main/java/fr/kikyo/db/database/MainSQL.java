package fr.kikyo.db.database;

import fr.kikyo.db.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainSQL {
    private Main main;

    private Connection connection;
    public String host, database, user, pass;
    public int port;

    public void connect(Main main){
        this.main = main;
        host = main.getConfig().getString("host");
        database = main.getConfig().getString("name");
        user = main.getConfig().getString("user");
        pass = main.getConfig().getString("pass");
        port = main.getConfig().getInt("port");

        System.out.println(host + database + user + pass + String.valueOf(port));

        try{
            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.pass));
                System.out.println("Database connection established");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void close() {
        try {
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void disconnect() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }

    }
        public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }


}
