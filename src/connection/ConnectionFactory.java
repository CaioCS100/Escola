package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.faces.context.FacesContext;

public class ConnectionFactory {
	
	private static final String DRIVER_CLASS = "org.postgresql.Driver";
	public static Connection getConnection()
    {
        Connection conexao = null;
        try (InputStream input = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/config.properties")) {
        	
            Properties prop = new Properties();
            
           prop.load(input);

           Class.forName(DRIVER_CLASS);
           conexao = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
           conexao.setAutoCommit(false);

       } catch (IOException | ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
       }

        return conexao;
    }
}