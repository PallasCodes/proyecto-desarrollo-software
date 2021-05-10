package Dominio;

public class ReporteParcial {
    private int id;
    private String matricula;
    private String actividades;
    private String estado;
    private String evaluacion;
    private String fecha;

    public static ReporteParcial reporteSeleccionado;

    public ReporteParcial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public static ReporteParcial getReporteSeleccionado() {
        return reporteSeleccionado;
    }

    public static void setReporteSeleccionado(ReporteParcial reporteSeleccionado) {
        ReporteParcial.reporteSeleccionado = reporteSeleccionado;
    }
}
