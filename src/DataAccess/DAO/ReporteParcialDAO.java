package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IReporteParcial;
import Dominio.ReporteMensual;
import Dominio.ReporteParcial;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

public class ReporteParcialDAO implements IReporteParcial {
    @Override
    public ArrayList<ReporteParcial> obtenerReportesParciales() {
        Connection conexion = Conexion.conectar();
        ArrayList<ReporteParcial> reportes = new ArrayList<>();

        String query = "SELECT * FROM reporte_parcial WHERE eliminado=0";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ReporteParcial reporte = new ReporteParcial();
                reporte.setId(rs.getInt("reporte_id"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setActividades(rs.getString("actividades"));
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
    public boolean evaluarReporteParcial(String evaluacion, int id) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE reporte_parcial SET evaluacion=? WHERE reporte_id=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,evaluacion);
            preparedStatement.setInt(2,id);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al evaluar el reporte parcial. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<ReporteParcial> obtenerReportesParcialesPorMat(String matricula) {
        Connection conexion = Conexion.conectar();
        ArrayList<ReporteParcial> reportes = new ArrayList<>();

        String query = "SELECT * FROM reporte_parcial WHERE eliminado=0 AND matricula='"+matricula+"'";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ReporteParcial reporte = new ReporteParcial();
                reporte.setId(rs.getInt("reporte_id"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setActividades(rs.getString("actividades"));
                reporte.setHoras(100);
                reporte.setFecha(rs.getString("fecha_entrega"));
                reporte.setEvaluacion(rs.getString("evaluacion"));
                reporte.setTipo("Parcial");

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
    public boolean generarReporteParcial(String matricula, String actividades) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO reporte_parcial (matricula,actividades) VALUES(?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,matricula);
            preparedStatement.setString(2,actividades);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al generar el reporte parcial. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ReporteParcial obtenerDetalles(int id) {
        Connection conexion = Conexion.conectar();
        ReporteParcial reporte = new ReporteParcial();

        String query = "SELECT * FROM reporte_parcial rep INNER JOIN practicante prac ON rep.matricula=prac.matricula INNER JOIN proyecto proy " +
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
                reporte.setTipo("Parcial");
                reporte.setProyecto(rs.getString("nombre"));
                reporte.setOrganizacion(rs.getString("organizacion"));
            }
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reporte;
    }
}
