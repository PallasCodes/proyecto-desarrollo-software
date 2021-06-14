package DataAccess.Interfaces;

import Dominio.Usuario;

import java.util.ArrayList;

public interface ICoordinador {
    ArrayList<Usuario> obtenerCoordinadores();

    boolean eliminarCoordinador(String matricula);

    boolean actualizarCoord(Usuario coordinador);
}
