package coord;

import Dominio.OrganizacionVinculada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;
import DataAccess.DAO.OrganizacionVinculadaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaOrganizacionesController implements Initializable {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
    ArrayList<OrganizacionVinculada> organizaciones;
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private TableView<OrganizacionVinculada> tablaOrgs;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colNombre;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colDireccion;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colCorreo;
    @FXML
    private TableColumn<OrganizacionVinculada, String> colId;
    @FXML
    private Button agregarOrg;
    Stage stage;
    Scene scene;


    // inicializar vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("direccion"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("correo"));
        colId.setCellValueFactory(new PropertyValueFactory<OrganizacionVinculada, String>("organizacion_id"));

        popularTabla();
    }


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaOrganizacionesController instance;

    public TablaOrganizacionesController(){
        instance = this;
    }

    public static TablaOrganizacionesController getInstance(){
        return instance;
    }


    // métodos de la UI
    @FXML
    public void irTablaProyectos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaProyectos.fxml");
    }

    @FXML
    public void irInicio(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/CoordInicio.fxml");
    }

    @FXML
    public void modalAgregarOrg(ActionEvent actionEvent) {
        SceneSwitcher switcher = new SceneSwitcher();
        Stage stageActual = (Stage) agregarOrg.getScene().getWindow();
        try {
            switcher.createDialog(stageActual, "/coord/ModalRegistrarOrg.fxml");
        } catch (IOException ioException) {
           ioException.printStackTrace();
        }
    }

    @FXML
    public void eliminarOrg(){
        OrganizacionVinculada org = obtenerOrgSeleccionada();
        if(org == null){
            alert.errorAlert("Error. Seleccione una organización");
        }else if(alert.confirmationAlert("¿Desea eliminar la organización?")){
            int organizacionId = Integer.parseInt(org.getOrganizacionId());
            orgDao.eliminarOrganizacion(organizacionId);
            popularTabla();
        }
    }

    // métodos
    public void popularTabla() {
        organizaciones = orgDao.obtenerOrganizaciones();
        tablaOrgs.getItems().setAll(organizaciones);
    }

    public OrganizacionVinculada obtenerOrgSeleccionada(){
        return tablaOrgs.getSelectionModel().getSelectedItem();
    }
}
