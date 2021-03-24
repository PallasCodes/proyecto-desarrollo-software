package data.DAO;

import Dominio.OrganizacionVinculada;
import data.Interfaces.IOrganizacionVinculadaDAO;
import data.Conexion;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                org.setOrganizacionId(rs.getInt("organizacion_id"));
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
}
