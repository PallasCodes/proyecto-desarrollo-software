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
                reporte.setEstado(rs.getString("estado"));
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
}
