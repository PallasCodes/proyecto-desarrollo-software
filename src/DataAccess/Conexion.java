package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String db = "renshu";
    private static String user = "root";
    private static String password = "";

    public static Connection conexion = null;

    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/" + db + "?user=" + user + "&pasword=" + password;
        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error de conexion de Bd");
        }
        return conexion;
    }
}