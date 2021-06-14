package DataAccess.DAO;

import Dominio.OrganizacionVinculada;
import DataAccess.Interfaces.IOrganizacionVinculadaDAO;
import DataAccess.Conexion;
import utils.AlertBuilder;
import java.sql.*;
import java.util.ArrayList;

public class OrganizacionVinculadaDAO implements IOrganizacionVinculadaDAO {
    // obtiene todas las ORGANIZACIONES VINCULADAS de la BD y las retorna en un ArrayList
    @Override
    public ArrayList<OrganizacionVinculada> obtenerOrganizaciones() {
        Connection conexion = Conexion.conectar();
        ArrayList<OrganizacionVinculada> organizaciones = new ArrayList<>();

        String query = "SELECT * FROM organizacion_vinculada WHERE eliminado=0";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                OrganizacionVinculada org = new OrganizacionVinculada();
                org.setNombre(rs.getString("nombre"));
                org.setCorreo(rs.getString("correo"));
                org.setDireccion(rs.getString("direccion"));

                organizaciones.add(org);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al actualizar la organización. Inténtelo más tarde");
            throwables.printStackTrace();
        }

        return organizaciones;
    }

    @Override
    public void registrarOrganizacion(OrganizacionVinculada org) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO organizacion_vinculada (nombre, direccion, correo) VALUES (?,?,?)";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,org.getNombre());
            preparedStatement.setString(2,org.getDireccion());
            preparedStatement.setString(3,org.getCorreo());
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al registrar la organización. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminarOrganizacion(String nombre) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE organizacion_vinculada SET eliminado=1 WHERE nombre=?";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,nombre);
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al eliminar la organización. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
    }

    @Override
    public void actualizarOrganizacion(OrganizacionVinculada org, String nombre) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE organizacion_vinculada SET nombre=?, direccion=?, correo=? WHERE nombre=?";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,org.getNombre());
            preparedStatement.setString(2,org.getDireccion());
            preparedStatement.setString(3,org.getCorreo());
            preparedStatement.setString(4,nombre);
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al actualizar la organización. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
    }
}
