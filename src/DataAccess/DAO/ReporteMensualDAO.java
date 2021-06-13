package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IReporteMensual;
import Dominio.ReporteMensual;
import utils.AlertBuilder;
import java.sql.*;
import java.util.ArrayList;

public class ReporteMensualDAO implements IReporteMensual {
    @Override
    public ArrayList<ReporteMensual> obtenerReportesMensuales() {
        Connection conexion = Conexion.conectar();
        ArrayList<ReporteMensual> reportes = new ArrayList<>();

        String query = "SELECT * FROM reporte_mensual WHERE eliminado=0";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ReporteMensual reporte = new ReporteMensual();
                reporte.setId(rs.getInt("reporte_id"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setActividades(rs.getString("actividades"));
                reporte.setHoras(rs.getInt("horas"));
                reporte.setFecha(rs.getString("fecha_entrega"));
                reporte.setEvaluacion(rs.getString("evaluacion"));

                reportes.add(reporte);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al obtener reportes. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return reportes;
    }

    @Override
    public boolean evaluarReporteMensual(String evaluacion, int id) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE reporte_mensual SET evaluacion=? WHERE reporte_id=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,evaluacion);
            preparedStatement.setInt(2,id);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al evaluar el reporte mensual. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean generarReporte(String matricula, int horas, String actividades) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO reporte_mensual (matricula,horas,actividades) VALUES(?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,matricula);
            preparedStatement.setInt(2,horas);
            preparedStatement.setString(3,actividades);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al generar el reporte mensual. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<ReporteMensual> obtenerReportesMensualesPorMat(String matricula) {
        Connection conexion = Conexion.conectar();
        ArrayList<ReporteMensual> reportes = new ArrayList<>();

        String query = "SELECT * FROM reporte_mensual WHERE eliminado=0 AND matricula='"+matricula+"'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ReporteMensual reporte = new ReporteMensual();
                reporte.setId(rs.getInt("reporte_id"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setActividades(rs.getString("actividades"));
                reporte.setHoras(rs.getInt("horas"));
                reporte.setFecha(rs.getString("fecha_entrega"));
                reporte.setEvaluacion(rs.getString("evaluacion"));
                reporte.setTipo("Mensual");

                reportes.add(reporte);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al obtener reportes. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return reportes;
    }

    @Override
    public ReporteMensual obtenerDetalles(int id) {
        Connection conexion = Conexion.conectar();
        ReporteMensual reporte = new ReporteMensual();

        String query = "SELECT * FROM reporte_mensual rep INNER JOIN practicante prac ON rep.matricula=prac.matricula INNER JOIN proyecto proy " +
                "ON proy.proyecto_id=prac.proyecto_id WHERE rep.reporte_id="+id;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                reporte.setId(rs.getInt("reporte_id"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setActividades(rs.getString("actividades"));
                reporte.setHoras(rs.getInt("horas"));
                reporte.setFecha(rs.getString("fecha_entrega"));
                reporte.setEvaluacion(rs.getString("evaluacion"));
                reporte.setTipo("Mensual");
                reporte.setProyecto(rs.getString("nombre"));
                reporte.setOrganizacion(rs.getString("organizacion"));
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al obtener reportes. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return reporte;
    }
}
