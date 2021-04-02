package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IPracticante;
import Dominio.Practicante;
import utils.AlertBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PracticanteDAO implements IPracticante {
    @Override
    public boolean registrarPracticante(Practicante practicante) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO practicante (matricula, estado, usuario_id, periodo) VALUES (?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,practicante.getMatricula());
            preparedStatement.setString(2,practicante.getEstado());
            preparedStatement.setInt(3,practicante.getUsuarioId());
            preparedStatement.setInt(4,practicante.getPeriodo());
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al registrar el practicante. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
