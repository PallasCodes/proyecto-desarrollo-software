package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String db = "bkng1mjq8daooj6jkrj5";
    private static String user = "uz19i3kuavo7kbcy";
    private static String password = "qMypffcz0ciARaAQUVQ1";

    public static Connection conexion = null;

    public static Connection conectar() {
        String url = "jdbc:mysql://uz19i3kuavo7kbcy:qMypffcz0ciARaAQUVQ1@bkng1mjq8daooj6jkrj5-mysql.services.clever-cloud.com:3306/bkng1mjq8daooj6jkrj5";
        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error de conexion de Bd");
        }
        return conexion;
    }
}