package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IUsuario;
import Dominio.Proyecto;
import Dominio.Usuario;
import utils.AlertBuilder;

import java.sql.*;

public class UsuarioDAO implements IUsuario {
    @Override
    public boolean registrarUsuario(Usuario usuario) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO usuario (contraseña, rol, nombre, primer_apellido, segundo_apellido, telefono, correo"
                + ", facultad) VALUES (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,usuario.getContraseña());
            preparedStatement.setString(2, usuario.getRol());
            preparedStatement.setString(3,usuario.getNombre());
            preparedStatement.setString(4,usuario.getPrimerApe());
            preparedStatement.setString(5,usuario.getSegundoApe());
            preparedStatement.setString(6,usuario.getTelefono());
            preparedStatement.setString(7,usuario.getCorreo());
            preparedStatement.setString(8,usuario.getFacultad());
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al registrar el usuario. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int obtenerIdUsuario(String correo) {
        Connection conexion = Conexion.conectar();
        Proyecto proyecto = new Proyecto();

        String query = "SELECT usuario_id FROM usuario WHERE correo='"+correo+"'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                return rs.getInt("usuario_id");
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return -1;
    }
}
