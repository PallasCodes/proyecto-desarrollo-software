package coord;

import DataAccess.DAO.ProyectoDAO;
import Dominio.Proyecto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaProyectosController implements Initializable {
    SceneSwitcher sw = new SceneSwitcher();
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    ArrayList<Proyecto> proyectos;
    AlertBuilder alert = new AlertBuilder();

    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> colNombre;
    @FXML
    private TableColumn<Proyecto, String> colOrganizacion;
    @FXML
    private TableColumn<Proyecto, String> colCupo;
    @FXML
    private TableColumn<Proyecto, String> colEstado;
    @FXML
    private TableColumn<Proyecto, String> colId;

    @FXML
    private Button agregarProyecto;
    @FXML
    private Button btnEliminar;

    private static TablaProyectosController instance;

    public TablaProyectosController(){
        instance = this;
    }

    public static TablaProyectosController getInstance(){
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("nombre"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("organizacion"));
        colCupo.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("cupo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("estado"));
        colId.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("proyecto_id"));

        popularTabla();
    }

    public void modalAgregarProyecto(ActionEvent actionEvent) {
        SceneSwitcher switcher = new SceneSwitcher();
        Stage stageActual = (Stage) agregarProyecto.getScene().getWindow();
        try {
            switcher.createDialog(stageActual, "/coord/ModalRegistrarPro.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void popularTabla() {
        proyectos = proyectoDAO.obtenerProyectos();
        tablaProyectos.getItems().setAll(proyectos);
    }

    public Proyecto obtenerOrgSeleccionada(){
        return tablaProyectos.getSelectionModel().getSelectedItem();
    }

    public void eliminarOrg(){
        Proyecto proyecto = obtenerOrgSeleccionada();
        if(proyecto == null){
            alert.errorAlert("Error. Seleccione un proyecto");
        }else if(alert.confirmationAlert("¿Desea eliminar el proyecto?")){
            int proyectoId = Integer.parseInt(proyecto.getProyectoId());
            proyectoDAO.eliminarProyecto(proyectoId);
            popularTabla();
        }
    }
}
