package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IAutoevaluacion;
import Dominio.Autoevaluacion;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

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

    @Override
    public ArrayList<Autoevaluacion> obtenerAutoevaluaciones(String matricula) {
        Connection conexion = Conexion.conectar();
        ArrayList<Autoevaluacion> autoevaluaciones = new ArrayList<>();
        String query = "SELECT * FROM autoevaluacion a LEFT JOIN practicante pra ON pra.matricula=a.matricula LEFT JOIN " +
                " usuario u ON u.matricula=pra.matricula_profesor WHERE pra.matricula_profesor='"+matricula+"'";
        try{
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Autoevaluacion autoevaluacion = new Autoevaluacion();

                autoevaluacion.setRespuesta1(rs.getInt("respuesta1"));
                autoevaluacion.setRespuesta2(rs.getInt("respuesta2"));
                autoevaluacion.setRespuesta3(rs.getInt("respuesta3"));
                autoevaluacion.setRespuesta4(rs.getInt("respuesta4"));
                autoevaluacion.setRespuesta5(rs.getInt("respuesta5"));
                autoevaluacion.setRespuesta6(rs.getInt("respuesta6"));
                autoevaluacion.setRespuesta7(rs.getInt("respuesta7"));
                autoevaluacion.setRespuesta8(rs.getInt("respuesta8"));
                autoevaluacion.setRespuesta9(rs.getInt("respuesta9"));
                autoevaluacion.setMatricula(rs.getString("matricula"));
                autoevaluacion.setFecha(rs.getString("fecha"));

                autoevaluaciones.add(autoevaluacion);
            }
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return autoevaluaciones;
    }
}
