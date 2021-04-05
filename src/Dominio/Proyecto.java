package Dominio;

public class Proyecto {
    // atributos
    private String proyectoId;
    private String nombre;
    private String organizacion;
    private String cupo;
    private String estado;
    private String descripcion;
    private String actividades;

    public static Proyecto proyectoSeleccionado = null;


    // constructores
    public Proyecto() {
    }

    public Proyecto(String proyectoId, String nombre, String organizacion, String cupo, String estado) {
        this.proyectoId = proyectoId;
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.cupo = cupo;
        this.estado = estado;
    }


    // geters y seters
    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }
}
