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
        return true;
    }
}
