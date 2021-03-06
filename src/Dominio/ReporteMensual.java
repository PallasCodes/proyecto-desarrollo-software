package Dominio;

public class ReporteMensual {
    private int id;
    private String matricula;
    private int horas;
    private String estado;
    private String fecha;
    private String actividades;
    private String evaluacion;
    private String tipo;
    private String organizacion;
    private String proyecto;
    private String practicante;

    public static ReporteMensual reporteSeleccionado;


    public ReporteMensual() {
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getPracticante() {
        return practicante;
    }

    public void setPracticante(String practicante) {
        this.practicante = practicante;
    }

    public static ReporteMensual getReporteSeleccionado() {
        return reporteSeleccionado;
    }

    public static void setReporteSeleccionado(ReporteMensual reporteSeleccionado) {
        ReporteMensual.reporteSeleccionado = reporteSeleccionado;
    }
}
