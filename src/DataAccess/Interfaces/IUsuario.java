package DataAccess.Interfaces;

import Dominio.Usuario;

public interface IUsuario {
    boolean registrarUsuario(Usuario usuario);

    int obtenerIdUsuario(String correo);

    Usuario obtenerUsuarioPorMat(String matricula);

    boolean cambiarContraseña(String matricula, String contraseña);

    String obtenerContraseña(int usuarioId);
}
