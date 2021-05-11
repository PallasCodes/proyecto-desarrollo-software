package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IPracticante;
import Dominio.Practicante;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

public class PracticanteDAO implements IPracticante {
    @Override
    public boolean registrarPracticante(Practicante practicante) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO practicante (matricula, estado, usuario_id, periodo) VALUES (?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,practicante.getMatricula());
            preparedStatement.setString(2,practicante.getEstado());
            preparedStatement.setInt(3,practicante.getUsuarioId());
            preparedStatement.setString(4,practicante.getPeriodo());
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al registrar el practicante. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Practicante> obtenerPracticantes() {
        Connection conexion = Conexion.conectar();
        ArrayList<Practicante> practicantes = new ArrayList<>();

        String query = "SELECT pra.*, u.*, pro.nombre AS nombre_proyecto FROM practicante pra LEFT JOIN usuario u ON "
                + "u.matricula = pra.matricula LEFT JOIN proyecto pro ON pro.proyecto_id = pra.proyecto_id WHERE pra.eliminado=0;";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);


            while(rs.next()){
                Practicante practicante = new Practicante();
                practicante.setNombre(rs.getString("nombre"));
                practicante.setPrimerApellido(rs.getString("primer_apellido"));
                practicante.setSegundoApellido(rs.getString("segundo_apellido"));
                practicante.setProyecto(rs.getString("nombre_proyecto"));
                practicante.setPeriodo(rs.getString("periodo"));
                practicante.setMatricula(rs.getString("matricula"));

                practicantes.add(practicante);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return practicantes;
    }

    @Override
    public boolean eliminarPracticante(String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE practicante SET eliminado='1' WHERE matricula=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,matricula);
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al eliminar el practicante. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Practicante> obtenerPracticantesConSolicitud() {
        Connection conexion = Conexion.conectar();
        ArrayList<Practicante> practicantes = new ArrayList<>();

        String query = "SELECT p.*, u.* FROM practicante p INNER JOIN usuario u ON "
                + "p.matricula = u.matricula WHERE p.estado='solicitado'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Practicante practicante = new Practicante();
                practicante.setMatricula(rs.getString("matricula"));
                practicante.setNombre(rs.getString("nombre"));
                practicante.setPrimerApellido(rs.getString("primer_apellido"));
                practicante.setSegundoApellido(rs.getString("segundo_apellido"));
                practicantes.add(practicante);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return practicantes;
    }

    @Override
    public boolean cambiarEstado(String estado, String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE practicante SET estado=? WHERE matricula=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,estado);
            preparedStatement.setString(2,matricula);
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String obtenerEstadoPracticante(String matricula) {
        Connection conexion = Conexion.conectar();

        String query = "SELECT estado FROM practicante WHERE matricula='"+matricula+"'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                return rs.getString("estado");
            }
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
