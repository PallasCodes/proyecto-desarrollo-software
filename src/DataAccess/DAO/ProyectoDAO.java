package DataAccess.DAO;
import DataAccess.Conexion;
import Dominio.OrganizacionVinculada;
import utils.AlertBuilder;

import java.sql.*;
import java.util.ArrayList;

import DataAccess.Interfaces.IProyectoDAO;
import Dominio.Proyecto;

import java.util.ArrayList;

public class ProyectoDAO implements IProyectoDAO {
    @Override
    public ArrayList<Proyecto> obtenerProyectos() {
        Connection conexion = Conexion.conectar();
        ArrayList<Proyecto> proyectos = new ArrayList<>();

        String query = "SELECT `proyecto`.*, `organizacion_vinculada`.nombre AS org_nombre " +
                "FROM `proyecto` " +
                "LEFT JOIN `organizacion_vinculada` ON `proyecto`.`organizacion_id` = `organizacion_vinculada`.`organizacion_id`;";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Proyecto proyecto = new Proyecto();
                proyecto.setProyectoId(Integer.toString(rs.getInt("proyecto_id")));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setCupo(Integer.toString(rs.getInt("cupo")));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setOrganizacion(rs.getString("org_nombre"));

                proyectos.add(proyecto);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error de conexión con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }

        return proyectos;
    }

    @Override
    public void registrarProyecto(Proyecto proyecto) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO proyecto (organizacion_id, nombre, descripcion, actividades, cupo, disponibilidad, estado) " +
                "VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2, proyecto.getNombre());
            preparedStatement.setString(3,proyecto.getDescripcion());
            preparedStatement.setString(4,proyecto.getActividades());
            preparedStatement.setInt(5,Integer.parseInt(proyecto.getCupo()));
            preparedStatement.setInt(6,Integer.parseInt(proyecto.getCupo()));
            preparedStatement.setString(7,"Disponible");
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al registrar el proyecto. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminarProyecto(int proyectoId) {
        Connection conexion = Conexion.conectar();
        String query = "DELETE FROM proyecto WHERE proyecto_id=?";

        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,proyectoId);
            preparedStatement.execute();
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.errorAlert("Error al eliminar el proyecto. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
    }
}
