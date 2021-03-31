package data.DAO;

import Dominio.OrganizacionVinculada;
import data.Interfaces.IOrganizacionVinculadaDAO;
import data.Conexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class OrganizacionVinculadaDAO implements IOrganizacionVinculadaDAO {
    static Connection conexion = Conexion.conectar();

    // obtiene todas las ORGANIZACIONES VINCULADAS de la BD y las retorna en un ArrayList
    @Override
    public ArrayList<OrganizacionVinculada> obtenerOrganizaciones() {
        ArrayList<OrganizacionVinculada> organizaciones = new ArrayList<>();

        String query = "SELECT * FROM organizacion_vinculada";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                OrganizacionVinculada org = new OrganizacionVinculada();
                org.setOrganizacionId(Integer.toString(rs.getInt("organizacion_id")));
                org.setNombre(rs.getString("nombre"));
                org.setCorreo(rs.getString("correo"));
                org.setDireccion(rs.getString("direccion"));

                organizaciones.add(org);
            }
        } catch (SQLException throwables) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error de conexión");
            error.setContentText("No pudo conectarse con la base de datos. Intentelo más tarde.");

            throwables.printStackTrace();
        }

        return organizaciones;
    }

    @Override
    public void registrarOrganizacion(OrganizacionVinculada org) {
        String query = "INSERT INTO organizacion_vinculada (nombre, direccion, correo) VALUES (?,?,?)";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,org.getNombre());
            preparedStatement.setString(2,org.getDireccion());
            preparedStatement.setString(3,org.getCorreo());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminarOrganizacion(int organizacionId) {
        String query = "DELETE FROM organizacion_vinculada WHERE organizacion_id=?";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,organizacionId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
