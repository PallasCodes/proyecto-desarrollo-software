package DataAccess.Interfaces;

import Dominio.ReporteParcial;

import java.util.ArrayList;

public interface IReporteParcial {
    ArrayList<ReporteParcial> obtenerReportesParciales();

    boolean evaluarReporteParcial(String evaluacion, int id);

    ArrayList<ReporteParcial> obtenerReportesParcialesPorMat(String matricula);

    boolean generarReporteParcial(String matricula, String actividades);
}
