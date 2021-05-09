package coord;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.Practicante;
import Dominio.Proyecto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaSolicitudesController implements Initializable {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    PracticanteDAO pracDao = new PracticanteDAO();
    ArrayList<Practicante> practicantes;
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private TableView<Practicante> tablaPracticantesConSolicitud;
    @FXML
    private TableColumn<Practicante, String> colNombre;
    @FXML
    private TableColumn<Practicante, String> colMatricula;
    @FXML
    private TableColumn<Practicante, String> colApellidoPaterno;
    @FXML
    private TableColumn<Practicante, String> colApellidoMaterno;
    Stage stage;
    Scene scene;


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaSolicitudesController instance;

    public TablaSolicitudesController(){
        instance = this;
    }

    public static TablaSolicitudesController getInstance(){
        return instance;
    }


    // inicializar vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Practicante, String>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<Practicante, String>("PrimerApellido"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<Practicante, String>("SegundoApellido"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Practicante, String>("matricula"));

        popularTabla();
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
    public void irTablaPracticantes(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaPracticantes.fxml");
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) tablaPracticantesConSolicitud.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void modalSolicitud(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Stage stageActual = (Stage) tablaPracticantesConSolicitud.getScene().getWindow();
            try {
                Practicante.practicanteSeleccionado = obtenerPracSeleccionado();
                sw.createDialog(stageActual, "/coord/ModalSolicitud.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void popularTabla() {
        practicantes = pracDao.obtenerPracticantesConSolicitud();
        tablaPracticantesConSolicitud.getItems().setAll(practicantes);
    }

    public Practicante obtenerPracSeleccionado(){
        Practicante.practicanteSeleccionado = tablaPracticantesConSolicitud.getSelectionModel().getSelectedItem();
        return Practicante.practicanteSeleccionado;
    }
}
