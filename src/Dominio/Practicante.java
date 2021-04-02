package Dominio;

public class Practicante {
    // atributos
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String contraseña;
    private String telefono;
    private String facultad;
    private String correo;
    private String matricula;
    private int periodo;
    private String estado;
    private int usuarioId;


    // constructores
    public Practicante() {
    }

    public Practicante(String nombre, String primerApellido, String segundoApellido, String contraseña, String telefono,
                       String facultad, String correo, String matricula, int periodo, String estado) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.facultad = facultad;
        this.correo = correo;
        this.matricula = matricula;
        this.periodo = periodo;
        this.estado = estado;
    }


    // geters y seters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public int getUsuarioId(){ return usuarioId; }

    public void setUsuarioId(int usuarioId){ this.usuarioId = usuarioId; }
}
