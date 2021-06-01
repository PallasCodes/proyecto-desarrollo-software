package DataAccess.Interfaces;

import Dominio.Autoevaluacion;

public interface IAutoevaluacion {

    boolean enviarAutoevaluacion(Autoevaluacion autoevaluacion, String matricula);

    boolean realizoAutoevaluacion(String matricula);
}
