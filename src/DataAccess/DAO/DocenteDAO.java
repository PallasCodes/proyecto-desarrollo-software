package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IDocenteDAO;
import Dominio.Usuario;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

public class DocenteDAO implements IDocenteDAO {

    @Override
    public ArrayList<Usuario> obtenerDocentes() {
        Connection conexion = Conexion.conectar();
        ArrayList<Usuario> docentes = new ArrayList<>();
        String query = "SELECT * FROM usuario WHERE rol='profesor' AND eliminado=0";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Usuario docente = new Usuario();
                docente.setMatricula(rs.getString("matricula"));
                docente.setNombre(rs.getString("nombre"));
                docente.setPrimerApellido(rs.getString("primer_apellido"));
                docente.setSegundoApellido(rs.getString("segundo_apellido"));
                docente.setFacultad(rs.getString("facultad"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setRol(rs.getString("rol"));
                docente.setCorreo(rs.getString("correo"));
                docente.setContraseña(rs.getString("contraseña"));
                docentes.add(docente);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return docentes;
    }

    @Override
    public boolean eliminarDocente(String numeroPersonal) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE usuario SET eliminado = 1 WHERE matricula LIKE '%" + numeroPersonal + "%'" ;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al eliminar el docente. Inténtelo más tarde.");
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizarDocente(Usuario docente) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE usuario SET nombre=?, primer_apellido=?, segundo_apellido=?, facultad=?, telefono=?, " +
                "correo=? WHERE numeroPersonal LIKE '%" + docente.getMatricula() + "%'";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, docente.getNombre());
            preparedStatement.setString(2, docente.getPrimerApellido());
            preparedStatement.setString(3, docente.getSegundoApellido());
            preparedStatement.setString(4, docente.getFacultad());
            preparedStatement.setString(5, docente.getTelefono());
            preparedStatement.setString(6, docente.getCorreo());

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al actualizar el docente. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
