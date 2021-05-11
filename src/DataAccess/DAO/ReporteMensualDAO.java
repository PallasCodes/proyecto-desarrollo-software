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
                reporte.setEstado(rs.getString("estado"));
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
}