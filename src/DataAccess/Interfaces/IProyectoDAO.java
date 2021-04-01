package DataAccess.Interfaces;

import Dominio.Proyecto;
import java.util.ArrayList;

public interface IProyectoDAO {
    ArrayList<Proyecto> obtenerProyectos();

    void registrarProyecto(Proyecto proyecto);

    void eliminarProyecto(int proyectoId);
}
