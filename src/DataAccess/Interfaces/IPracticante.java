package DataAccess.Interfaces;

import Dominio.Practicante;
import java.util.ArrayList;

public interface IPracticante {
    boolean registrarPracticante(Practicante practicante);

    ArrayList<Practicante> obtenerPracticantes();

    boolean eliminarPracticante(String matricula);
}
