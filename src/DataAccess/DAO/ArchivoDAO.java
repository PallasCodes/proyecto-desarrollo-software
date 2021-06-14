package DataAccess.DAO;

import DataAccess.Conexion;
import DataAccess.Interfaces.IArchivoDAO;
import Dominio.Archivo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ArchivoDAO implements IArchivoDAO {


    @Override
    public int guardarArchivo(Archivo archivo) throws SQLException {
        int guardadoExitoso;
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO archivo (nombreArchivo, tipoArchivo, direccionArchivo, fecha_subida, matricula) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, archivo.getNombreArchivo());
        preparedStatement.setString(2, archivo.getTipoArchivo());
        preparedStatement.setString(3, archivo.getDireccionArchivo());
        java.sql.Date fechaRegistro = new java.sql.Date(new java.util.Date().getTime());
        preparedStatement.setDate(4, fechaRegistro);
        preparedStatement.setString(5, archivo.getMatricula());
        guardadoExitoso = preparedStatement.executeUpdate();
        return guardadoExitoso;
    }

    @Override
    public ArrayList<Archivo> obtenerArchivos() throws SQLException {
        Archivo archivo;
        ArrayList<Archivo> todosLosArchivos = new ArrayList<>();
        Connection conexion = Conexion.conectar();
        String query = "SELECT * FROM archivo";
        PreparedStatement preparedStatement = conexion.prepareStatement(query);
        ResultSet resultados = preparedStatement.executeQuery();
        while (resultados.next()) {
            archivo = new Archivo();
            archivo.setIdArchivo(resultados.getInt("archivo_id"));
            archivo.setNombreArchivo(resultados.getString("nombreArchivo"));
            archivo.setTipoArchivo(resultados.getString("tipoArchivo"));
            java.util.Date fechaRegistro = new java.util.Date(resultados.getDate("fecha_subida").getTime());
            archivo.setFechaRegistro(fechaRegistro);
            archivo.setDireccionArchivo(resultados.getString("direccionArchivo"));
            archivo.setMatricula(resultados.getString("matricula"));
            archivo.setEliminado(resultados.getInt("eliminado"));
            todosLosArchivos.add(archivo);
        }
        return todosLosArchivos;
    }
}
