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

// TODO: generate monthly report
// TODO: generate partial report
// TODO: self-evaluation
// TODO: host DB online
// TODO: merge Lira's code
// TODO: fix second iteration code
// TODO: fix first iteration code
// TODO: add extra stuff