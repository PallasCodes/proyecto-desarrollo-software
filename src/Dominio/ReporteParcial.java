package Dominio;

public class ReporteParcial {
    private int id;
    private String matricula;
    private String actividades;
    private String estado;
    private String evaluacion;
    private String fecha;
    private String organizacion;
    private String proyecto;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    private String tipo;
    private int horas;

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

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
}
