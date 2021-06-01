package DataAccess.Interfaces;

import DataAccess.DAO.PracticanteDAO;
import Dominio.Practicante;
import java.util.ArrayList;

public interface IPracticante {
    boolean registrarPracticante(Practicante practicante);

    ArrayList<Practicante> obtenerPracticantes();

    boolean eliminarPracticante(String matricula);

    ArrayList<Practicante> obtenerPracticantesConSolicitud();

    boolean cambiarEstado(String estado, String matricula);

    String obtenerEstadoPracticante(String matricula);

    int obtenerHoras(String matricula);

    boolean actualizarPracticante(Practicante practicante);

    void aumentarHoras(String matricula, int horas);
}
