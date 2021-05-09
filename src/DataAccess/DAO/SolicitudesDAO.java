package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.ISolicitud;
import Dominio.Solicitud;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

public class SolicitudesDAO implements ISolicitud {
    @Override
    public ArrayList<Solicitud> obtenerSolicitudesPracticante(String matricula) {
        Connection conexion = Conexion.conectar();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        String query = "select sp.*, p.* from solicitud_proyecto sp inner join proyecto p " +
                "on sp.proyecto_id = p.proyecto_id WHERE matricula='"+matricula+"'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getInt("solicitud_id"));
                solicitud.setProyectoId(rs.getInt("proyecto_id"));
                solicitud.setProyecto(rs.getString("nombre"));
                solicitud.setOrganizacion(rs.getString("organizacion"));
                solicitud.setDisponibilidad(rs.getInt("disponibilidad"));
                solicitud.setCupo(rs.getInt("cupo"));
                solicitudes.add(solicitud);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al obtener solicitudes. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return solicitudes;
    }

    @Override
    public boolean asignarProyecto(int proyectoId, String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE practicante SET proyecto_id=?, estado=? WHERE matricula=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,proyectoId);
            preparedStatement.setString(2,"asignado");
            preparedStatement.setString(3, matricula);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al asignar proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void disminuirDisponibilidadProyecto(int proyectoId) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE proyecto SET disponibilidad=disponibilidad - 1 WHERE proyecto_id=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, proyectoId);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al asignar proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminarSolicitudes(String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "DELETE FROM solicitud_proyecto WHERE matricula=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, matricula);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al asignar proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
        }
    }
}
