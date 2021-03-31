package DataAccess.Interfaces;

import Dominio.OrganizacionVinculada;

import java.util.ArrayList;

public interface IOrganizacionVinculadaDAO {
    ArrayList<OrganizacionVinculada> obtenerOrganizaciones();

    void registrarOrganizacion(OrganizacionVinculada org);

    void eliminarOrganizacion(int organizacionId);
}
