package DataAccess;

import utils.AlertBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioData {
    public static Connection conexion = Conexion.conectar();

    public static String iniciarSesion(String correo, String contraseña){
        String query = "SELECT * FROM usuario WHERE correo='"+correo+"' AND contraseña='"+contraseña+"'";

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
              return rs.getString("rol");
            }
        } catch (Exception throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return "";
    }
}
