package Dominio;

public class OrganizacionVinculada {
    // ATRIBUTOS
    private String organizacionId;
    private String nombre;
    private String direccion;
    private String correo;


    // GETS Y SETS
    public String getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(String organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    // CONSTRUCTORES
    public OrganizacionVinculada() {
    }

    public OrganizacionVinculada(String organizacionId, String nombre, String direccion, String correo) {
        this.organizacionId = organizacionId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }
}
