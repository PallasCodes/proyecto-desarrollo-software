package DataAccess.Interfaces;

import Dominio.ReporteMensual;

import java.util.ArrayList;

public interface IReporteMensual {
    ArrayList<ReporteMensual> obtenerReportesMensuales();

    boolean evaluarReporteMensual(String evaluacion, int id);

    boolean generarReporte(String matricula, int horas, String actividades);

    ArrayList<ReporteMensual> obtenerReportesMensualesPorMat(String matricula);

    ReporteMensual obtenerDetalles(int id);
}
