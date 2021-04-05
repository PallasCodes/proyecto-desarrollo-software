package coord;

import DataAccess.DAO.ProyectoDAO;
import Dominio.Proyecto;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaProyectosController implements Initializable {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    ArrayList<Proyecto> proyectos;
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
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
    Stage stage;
    Scene scene;


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaProyectosController instance;

    public TablaProyectosController(){
        instance = this;
    }

    public static TablaProyectosController getInstance(){
        return instance;
    }


    // inicializar vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("nombre"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("organizacion"));
        colCupo.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("cupo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("estado"));
        colId.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("proyecto_id"));

        popularTabla();
    }


    // métodos de la UI
    @FXML
    public void modalAgregarProyecto(ActionEvent actionEvent) {
        SceneSwitcher switcher = new SceneSwitcher();
        Stage stageActual = (Stage) agregarProyecto.getScene().getWindow();
        try {
            switcher.createDialog(stageActual, "/coord/ModalRegistrarPro.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void eliminarPro(){
        Proyecto proyecto = obtenerProSeleccionado();
        if(proyecto == null){
            alert.errorAlert("Error. Seleccione un proyecto");
        }else if(alert.confirmationAlert("¿Desea eliminar el proyecto?")){
            int proyectoId = Integer.parseInt(Proyecto.proyectoSeleccionado.getProyectoId());
            if(proyectoDAO.eliminarProyecto(proyectoId)) {
                Proyecto.proyectoSeleccionado = null;
                popularTabla();
                // si sucede una excepción, no se actualiza la tabla
            }
        }
    }

    @FXML
    public void actualizarPro(ActionEvent actionEvent) {
        Proyecto proyecto = obtenerProSeleccionado();
        if(proyecto == null){
            alert.errorAlert("Error. Seleccione un proyecto");
        }else {
            SceneSwitcher switcher = new SceneSwitcher();
            Stage stageActual = (Stage) agregarProyecto.getScene().getWindow();
            try {
                switcher.createDialog(stageActual, "/coord/ModalActualizarPro.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @FXML
    public void irTablaOrganizaciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaOrganizaciones.fxml");
    }

    @FXML
    public void irInicio(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/CoordInicio.fxml");
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }


    // métodos
    public void popularTabla() {
        proyectos = proyectoDAO.obtenerProyectos();
        tablaProyectos.getItems().setAll(proyectos);
    }

    public Proyecto obtenerProSeleccionado(){
        Proyecto.proyectoSeleccionado = tablaProyectos.getSelectionModel().getSelectedItem();
        return Proyecto.proyectoSeleccionado;
    }
}
