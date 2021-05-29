package practicante;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.Proyecto;
import Dominio.Usuario;
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
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SolicitarProyectoController implements Initializable {
    // instancias de las clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    ProyectoDAO proyectoDao = new ProyectoDAO();
    PracticanteDAO pracDao = new PracticanteDAO();
    ArrayList<Proyecto> proyectos;
    ArrayList<Proyecto> proyectosSeleccionados = new ArrayList<>();
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private Label inicio;
    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> colNombre;
    @FXML
    private TableColumn<Proyecto, String> colOrganizacion;
    @FXML
    private TableColumn<Proyecto, Integer> colDisponibilidad;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSolicitar;
    @FXML
    private Label lbError;
    @FXML
    private TableView<Proyecto> tablaProyectosSeleccionados;
    @FXML
    private TableColumn<Proyecto, String> colNombre1;
    @FXML
    private TableColumn<Proyecto, String> colOrganizacion1;

    private Stage stage;
    private Scene scene;


    // inicializar vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("nombre"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("organizacion"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Proyecto, Integer>("disponibilidad"));

        popularTabla();

        colNombre1.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("nombre"));
        colOrganizacion1.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("organizacion"));
    }


    // métodos de la UI
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) inicio.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void irTablaReportes(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/practicante/TablaReportes.fxml");
    }


    // métodos
    public void popularTabla() {
        proyectos = proyectoDao.obtenerProyectos();
        tablaProyectos.getItems().setAll(proyectos);
    }

    public Proyecto obtenerProSeleccionado(){
        Proyecto.proyectoSeleccionado = tablaProyectos.getSelectionModel().getSelectedItem();
        return Proyecto.proyectoSeleccionado;
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void deseleccionarProyecto(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Proyecto proyecto = tablaProyectosSeleccionados.getSelectionModel().getSelectedItem();
            tablaProyectosSeleccionados.getItems().remove(proyecto);
            proyectos.add(proyecto);
            proyectosSeleccionados.remove(proyecto);
            tablaProyectos.getItems().add(proyecto);
        }
    }

    @FXML
    void seleccionarProyecto(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Proyecto proyecto = obtenerProSeleccionado();
            proyectos.remove(proyecto);
            proyectosSeleccionados.add(proyecto);
            tablaProyectos.getItems().remove(proyecto);
            tablaProyectosSeleccionados.getItems().add(proyecto);
        }
    }

    @FXML
    void solicitarProyecto(ActionEvent event) throws IOException{
        if(validarSelecciones()){
            if(proyectoDao.solicitarProyecto(proyectosSeleccionados, Usuario.usuarioActual.getMatricula())){
                if(pracDao.cambiarEstado("solicitado",Usuario.usuarioActual.getMatricula())){
                    alert.successAlert("Solicitud enviada con éxito");
                    sw.switchScene(event, stage, scene, "/practicante/PracticanteInicio.fxml");
                }
            }
        } else {
            alert.errorAlert("Solo puedes seleccionar 1 a 3 proyectos");
        }
    }

    boolean validarSelecciones(){
        if(tablaProyectosSeleccionados.getItems().size() <= 0 || tablaProyectosSeleccionados.getItems().size() > 3){
            return false;
        }
        return true;
    }

    public void irPerfil(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "/practicante/PracticanteInicio.fxml");
    }
}
