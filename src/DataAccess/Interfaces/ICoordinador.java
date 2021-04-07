package DataAccess.Interfaces;

import Dominio.Usuario;

import java.util.ArrayList;

public interface ICoordinador {
    ArrayList<Usuario> obtenerCoordinadores();

    boolean eliminarCoordinador(int coordId);

    boolean actualizarCoord(Usuario coordinador);
}
