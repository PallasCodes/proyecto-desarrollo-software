package DataAccess.Interfaces;

import Dominio.Usuario;

import java.util.ArrayList;

public interface IDocenteDAO {

    public ArrayList<Usuario> obtenerDocentes();
    public boolean eliminarDocente(String numeroPersonal);
    public boolean actualizarDocente(Usuario docente);
}