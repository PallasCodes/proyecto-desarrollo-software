package coord;

import Dominio.OrganizacionVinculada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.SceneSwitcher;
import data.DAO.OrganizacionVinculadaDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaOrganizacionesController implements Initializable {
    SceneSwitcher sw = new SceneSwitcher();
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();

    @FXML
    private TableView<OrganizacionVinculada> tablaOrgs;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colNombre;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colDireccion;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colCorreo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("direccion"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("correo"));

        ArrayList<OrganizacionVinculada> organizaciones = orgDao.obtenerOrganizaciones();
        tablaOrgs.getItems().setAll(organizaciones);
    }

    @FXML
    void irAgregarOrg(ActionEvent event) {

    }

    public void popularTabla(){

    }

    public void irTablaProyectos(MouseEvent mouseEvent) {
    }


}
