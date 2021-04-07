package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.ICoordinador;
import Dominio.Practicante;
import Dominio.Usuario;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

public class CoordinadorDAO implements ICoordinador {
    @Override
    public ArrayList<Usuario> obtenerCoordinadores() {
        Connection conexion = Conexion.conectar();
        ArrayList<Usuario> coordinadores = new ArrayList<>();

        String query = "SELECT * FROM usuario WHERE rol='coord'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Usuario coordinador = new Usuario();
                coordinador.setNombre(rs.getString("nombre"));
                coordinador.setPrimerApe(rs.getString("primer_apellido"));
                coordinador.setSegundoApe(rs.getString("segundo_apellido"));
                coordinador.setFacultad(rs.getString("facultad"));
                coordinador.setUsuarioId(rs.getInt("usuario_id"));

                coordinadores.add(coordinador);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return coordinadores;
    }

    @Override
    public boolean eliminarCoordinador(int coordId) {
        Connection conexion = Conexion.conectar();
        String query = "DELETE FROM usuario WHERE usuario_id=?";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,coordId);
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al eliminar el coordinador. Inténtelo más tarde.");
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizarCoord(Usuario coordinador) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE usuario SET nombre=?, primer_apellido=?, segundo_apellido=?, facultad=?, telefono=?, " +
                "correo=? WHERE usuario_id="+coordinador.getUsuarioId();
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,coordinador.getNombre());
            preparedStatement.setString(2, coordinador.getPrimerApe());
            preparedStatement.setString(3,coordinador.getSegundoApe());
            preparedStatement.setString(4,coordinador.getFacultad());
            preparedStatement.setString(5, coordinador.getTelefono());
            preparedStatement.setString(6,coordinador.getCorreo());

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al actualizar el coordinador. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
