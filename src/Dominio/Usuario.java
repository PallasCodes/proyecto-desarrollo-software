package Dominio;

public class Usuario {
    // atributos
    private String contraseña;
    private String rol;
    private String nombre;
    private String primerApe;
    private String segundoApe;
    private String telefono;
    private String correo;
    private String facultad;
    private int usuarioId;

    public static Usuario coordinadorSeleccionado;
    public static int usuarioActual;


    // constructores
    public Usuario() {
    }

    public Usuario(String contraseña, String rol, String nombre, String primerApe, String segundoApe, String telefono,
                   String correo, String facultad, int usuarioId) {
        this.contraseña = contraseña;
        this.rol = rol;
        this.nombre = nombre;
        this.primerApe = primerApe;
        this.segundoApe = segundoApe;
        this.telefono = telefono;
        this.correo = correo;
        this.facultad = facultad;
        this.usuarioId = usuarioId;
    }


    // sets y gets
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApe() {
        return primerApe;
    }

    public void setPrimerApe(String primerApe) {
        this.primerApe = primerApe;
    }

    public String getSegundoApe() {
        return segundoApe;
    }

    public void setSegundoApe(String segundoApe) {
        this.segundoApe = segundoApe;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public int getUsuarioId() { return usuarioId; }

    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}
