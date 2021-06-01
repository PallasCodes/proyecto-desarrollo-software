package DataAccess.Interfaces;

import Dominio.OrganizacionVinculada;

import java.util.ArrayList;

public interface IOrganizacionVinculadaDAO {
    ArrayList<OrganizacionVinculada> obtenerOrganizaciones();

    void registrarOrganizacion(OrganizacionVinculada org);

    void eliminarOrganizacion(String nombre);

    void actualizarOrganizacion(OrganizacionVinculada org, String nombre);
}
