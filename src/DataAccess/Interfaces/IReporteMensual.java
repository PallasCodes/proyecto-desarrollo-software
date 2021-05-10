package DataAccess.Interfaces;

import Dominio.ReporteMensual;

import java.util.ArrayList;

public interface IReporteMensual {
    ArrayList<ReporteMensual> obtenerReportesMensuales();

    boolean evaluarReporteMensual(String evaluacion, int id);
}
