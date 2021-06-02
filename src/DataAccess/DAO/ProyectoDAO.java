package DataAccess.DAO;

import DataAccess.Conexion;
import utils.AlertBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import DataAccess.Interfaces.IProyectoDAO;
import Dominio.Proyecto;

public class ProyectoDAO implements IProyectoDAO {
    @Override
    public ArrayList<Proyecto> obtenerProyectos() {
        Connection conexion = Conexion.conectar();
        ArrayList<Proyecto> proyectos = new ArrayList<>();

        String query = "SELECT * FROM proyecto WHERE estado='disponible' AND eliminado=0 AND disponibilidad > 0";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Proyecto proyecto = new Proyecto();
                proyecto.setId(rs.getInt("proyecto_id"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setCupo(Integer.toString(rs.getInt("cupo")));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setOrganizacion(rs.getString("organizacion"));
                proyecto.setDisponibilidad(rs.getInt("disponibilidad"));

                proyectos.add(proyecto);
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return proyectos;
    }

    @Override
    public boolean registrarProyecto(Proyecto proyecto) {
        Connection conexion = Conexion.conectar();
        String query = "INSERT INTO proyecto (organizacion, nombre, descripcion, actividades, cupo, disponibilidad, estado) " +
                "VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,proyecto.getOrganizacion());
            preparedStatement.setString(2, proyecto.getNombre());
            preparedStatement.setString(3,proyecto.getDescripcion());
            preparedStatement.setString(4,proyecto.getActividades());
            preparedStatement.setInt(5,Integer.parseInt(proyecto.getCupo()));
            preparedStatement.setInt(6,Integer.parseInt(proyecto.getCupo()));
            preparedStatement.setString(7,"disponible");
            preparedStatement.execute();
            conexion.close();
        } catch (Exception ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al registrar el proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarProyecto(int id) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE proyecto SET eliminado=1 WHERE proyecto_id=?";
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al eliminar el proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizarProyecto(Proyecto proyecto) {
        Connection conexion = Conexion.conectar();
        String query = "UPDATE proyecto SET organizacion_id=?, nombre=?, descripcion=?, actividades=?, cupo=?, " +
                "estado=? WHERE proyecto_id="+proyecto.getId();
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2, proyecto.getNombre());
            System.out.println(proyecto.getNombre());
            preparedStatement.setString(3,proyecto.getDescripcion());
            preparedStatement.setString(4,proyecto.getActividades());
            preparedStatement.setInt(5, Integer.parseInt(proyecto.getCupo()));
            preparedStatement.setString(6,proyecto.getEstado());

            preparedStatement.execute();
            conexion.close();
        } catch (SQLException ex) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al actualizar el proyecto. Inténtelo más tarde.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Proyecto obtenerProyecto(int proyectoId) {
        Connection conexion = Conexion.conectar();
        Proyecto proyecto = new Proyecto();

        String query = "SELECT * FROM proyecto WHERE proyecto_id="+proyectoId;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                proyecto.setId(rs.getInt("proyecto_id"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setCupo(Integer.toString(rs.getInt("cupo")));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setOrganizacion(rs.getString("organizacion_id"));
                proyecto.setActividades(rs.getString("actividades"));
                proyecto.setDescripcion(rs.getString("descripcion"));
            }
            conexion.close();
        } catch (SQLException throwables) {
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al conectarse con la BD. Inténtelo más tarde.");
            throwables.printStackTrace();
        }
        return proyecto;
    }

    @Override
    public boolean solicitarProyecto(ArrayList<Proyecto> proyectos, String matricula) {
        Connection conexion = Conexion.conectar();
        AtomicInteger errores = new AtomicInteger();
        proyectos.forEach(proyecto -> {
            String query = "INSERT INTO solicitud_proyecto (matricula, proyecto_id) VALUES (?,?)";
            try{
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, matricula);
                preparedStatement.setInt(2,proyecto.getId());
                preparedStatement.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                errores.getAndIncrement();
            }
        });
        try{
            conexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(errores.get() > 0){
            AlertBuilder alert = new AlertBuilder();
            alert.exceptionAlert("Error al solicitar proyecto. Inténtelo más tarde.");
            return false;
        }
        return true;
    }
}
