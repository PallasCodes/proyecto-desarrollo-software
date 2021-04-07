package DataAccess.Interfaces;

import Dominio.Usuario;

public interface IUsuario {
    boolean registrarUsuario(Usuario usuario);

    int obtenerIdUsuario(String correo);

    Usuario obtenerUsuario(int usuarioId);

    boolean cambiarContraseña(int usuarioId, String contraseña);

    String obtenerContraseña(int usuarioId);
}
