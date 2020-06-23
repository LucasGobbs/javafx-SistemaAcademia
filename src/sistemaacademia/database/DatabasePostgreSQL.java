package sistemaacademia.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class DatabasePostgreSQL implements Database {

    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/prova012020", "postgres", "Vegetto21");
            //this.connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-86-170-8.compute-1.amazonaws.com:5432/d92h1kak33sd7v?sslmode=require&user=igfedantjwmzzn&password=193695b6da0fe2491a05964dfd8dc56276793bdbe631bd5ccfaf0d4eed0a11e4");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
