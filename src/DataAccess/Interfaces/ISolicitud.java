package DataAccess.Interfaces;

import Dominio.Solicitud;
import java.util.ArrayList;

public interface ISolicitud {
    ArrayList<Solicitud> obtenerSolicitudesPracticante(String matricula);

    boolean asignarProyecto(int proyectoId, String matricula);

    void disminuirDisponibilidadProyecto(int proyectoId);

    void eliminarSolicitudes(String matricula);
}
