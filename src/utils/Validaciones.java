package utils;

public class Validaciones {

    public boolean confirmarContraseña(String contraseña, String confirmacion) {
        if (contraseña.equals(confirmacion)) {
            return true;
        }
        return false;
    }

    public boolean validacionTelefono(String telefono) {
        String regexTelefono = "^\\d{10}$";
        boolean telefonoValido = telefono.matches(regexTelefono);
        return telefonoValido;
    }

    public boolean validacionNumeroPer(String numeroPersonal) {
        String regexNumeroPer = "^\\d{5}$";
        boolean numeroValido = numeroPersonal.matches(regexNumeroPer);
        return numeroValido;
    }
}