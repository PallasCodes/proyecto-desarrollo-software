package DataAccess.Interfaces;

import Dominio.Archivo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IArchivoDAO {

    public int guardarArchivo(Archivo archivo) throws SQLException;
    public ArrayList<Archivo> obtenerArchivos() throws SQLException;

}
