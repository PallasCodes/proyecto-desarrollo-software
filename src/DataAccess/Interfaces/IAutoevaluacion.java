package DataAccess.Interfaces;

import Dominio.Autoevaluacion;

import java.util.ArrayList;

public interface IAutoevaluacion {

    boolean enviarAutoevaluacion(Autoevaluacion autoevaluacion, String matricula);

    boolean realizoAutoevaluacion(String matricula);

    ArrayList<Autoevaluacion> obtenerAutoevaluaciones(String matricula);
}
