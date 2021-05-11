package DataAccess.Interfaces;

import Dominio.Proyecto;
import java.util.ArrayList;

public interface IProyectoDAO {
    ArrayList<Proyecto> obtenerProyectos();

    boolean registrarProyecto(Proyecto proyecto);

    boolean eliminarProyecto(int proyectoId);

    boolean actualizarProyecto(Proyecto proyecto);

    Proyecto obtenerProyecto(int proyectoId);

    boolean solicitarProyecto(ArrayList<Proyecto> proyectos, String matricula);
}
