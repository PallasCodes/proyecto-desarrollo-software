package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IAutoevaluacion;
import Dominio.Autoevaluacion;
import utils.AlertBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutoevaluacionDAO implements IAutoevaluacion {
    @Override
    public boolean enviarAutoevaluacion(Autoevaluacion autoevaluacion, String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO autoevaluacion (respuesta1, respuesta2, respuesta3, respuesta4, respuesta5," +
                "respuesta6,respuesta7,respuesta8,respuesta9, matricula) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,autoevaluacion.getRespuesta1());
            preparedStatement.setInt(2,autoevaluacion.getRespuesta2());
            preparedStatement.setInt(3,autoevaluacion.getRespuesta3());
            preparedStatement.setInt(4,autoevaluacion.getRespuesta4());
            preparedStatement.setInt(5,autoevaluacion.getRespuesta5());
            preparedStatement.setInt(6,autoevaluacion.getRespuesta6());
            preparedStatement.setInt(7,autoevaluacion.getRespuesta7());
            preparedStatement.setInt(8,autoevaluacion.getRespuesta8());
            preparedStatement.setInt(9,autoevaluacion.getRespuesta9());
            preparedStatement.setString(10,matricula);
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al enviar autoevaluación. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean realizoAutoevaluacion(String matricula) {
        Connection conexion = Conexion.conectar();
        String query = "SELECT * FROM  autoevaluacion WHERE matricula='"+matricula+"'";
        try{
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                return true;
            }
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
