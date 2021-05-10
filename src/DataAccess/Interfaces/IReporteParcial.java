package DataAccess.Interfaces;

import Dominio.ReporteParcial;

import java.util.ArrayList;

public interface IReporteParcial {
    ArrayList<ReporteParcial> obtenerReportesParciales();

    boolean evaluarReporteParcial(String evaluacion, int id);
}
