package Dominio;

import java.util.Date;

public class Archivo {

    private int idArchivo;
    private Date fechaRegistro;
    private String nombreArchivo;
    private String tipoArchivo;
    private String direccionArchivo;
    private String matricula;
    private int eliminado;

    public Archivo() {
    }

    public Archivo(int idArchivo, Date fechaRegistro, String nombreArchivo, String tipoArchivo,
                   String direccionArchivo, String matricula, int eliminado) {
        this.idArchivo = idArchivo;
        this.fechaRegistro = fechaRegistro;
        this.nombreArchivo = nombreArchivo;
        this.tipoArchivo = tipoArchivo;
        this.direccionArchivo = direccionArchivo;
        this.matricula = matricula;
        this.eliminado = eliminado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
