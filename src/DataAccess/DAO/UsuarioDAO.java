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
                + ", facultad, matricula) VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,usuario.getContraseña());
            preparedStatement.setString(2, usuario.getRol());
            preparedStatement.setString(3,usuario.getNombre());
            preparedStatement.setString(4,usuario.getPrimerApellido());
            preparedStatement.setString(5,usuario.getSegundoApellido());
            preparedStatement.setString(6,usuario.getTelefono());
            preparedStatement.setString(7,usuario.getCorreo());
            preparedStatement.setString(8,usuario.getFacultad());
            preparedStatement.setString(9,usuario.getMatricula());
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

    @Override
    public Usuario obtenerUsuarioPorMat(String matricula) {
        Connection conexion = Conexion.conectar();
        Usuario usuario = new Usuario();

        String query = "SELECT * FROM usuario WHERE matricula = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, matricula);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                usuario.setMatricula(matricula);
                usuario.setNombre(rs.getString("nombre"));
                usuario.setRol(rs.getString("rol"));
                usuario.setPrimerApellido(rs.getString("primer_apellido"));
                usuario.setSegundoApellido(rs.getString("segundo_apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFacultad(rs.getString("facultad"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return usuario;
    }

    @Override
    public boolean cambiarContraseña(String matricula, String contraseña) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE usuario SET contraseña=? WHERE matricula="+matricula;
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,contraseña);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al actualizar contraseña. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String obtenerContraseña(int usuarioId) {
        Connection conexion = Conexion.conectar();
        String contraseñaBd = "";

        String query = "SELECT contraseña FROM usuario WHERE usuario_id="+usuarioId;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                contraseñaBd = rs.getString("contraseña");
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return contraseñaBd;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE usuario SET nombre=?, primer_apellido=?, segundo_apellido=?, facultad=?," +
                "correo=?, telefono=? WHERE matricula=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,usuario.getNombre());
            preparedStatement.setString(2,usuario.getPrimerApellido());
            preparedStatement.setString(3,usuario.getSegundoApellido());
            preparedStatement.setString(4,usuario.getFacultad());
            preparedStatement.setString(5,usuario.getCorreo());
            preparedStatement.setString(6,usuario.getTelefono());
            preparedStatement.setString(7,usuario.getMatricula());

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al actualizar usuario. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
