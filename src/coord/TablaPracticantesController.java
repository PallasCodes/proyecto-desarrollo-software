package coord;

import DataAccess.DAO.PracticanteDAO;
import Dominio.Practicante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaPracticantesController implements Initializable {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    ArrayList<Practicante> practicantes;
    PracticanteDAO pracDao = new PracticanteDAO();


    // componentes de la UI
    @FXML
    private Button agregarPracticante;
    @FXML
    private TableView<Practicante> tablaPracticantes;
    @FXML
    private TableColumn<Practicante, String> colNombre;
    @FXML
    private TableColumn<Practicante, String> colPrimerApe;
    @FXML
    private TableColumn<Practicante, String> colSegundoApe;
    @FXML
    private TableColumn<Practicante, String> colId;
    @FXML
    private TableColumn<Practicante, String> colProyecto;
    @FXML
    private TableColumn<Practicante, String> colPeriodo;
    Stage stage;
    Scene scene;


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaPracticantesController instance;

    public TablaPracticantesController(){
        instance = this;
    }

    public static TablaPracticantesController getInstance(){
        return instance;
    }


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Practicante, String>("nombre"));
        colPrimerApe.setCellValueFactory(new PropertyValueFactory<Practicante, String>("PrimerApellido"));
        colSegundoApe.setCellValueFactory(new PropertyValueFactory<Practicante, String>("SegundoApellido"));
        colProyecto.setCellValueFactory(new PropertyValueFactory<Practicante, String>("proyecto"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<Practicante, String>("periodo"));

        popularTabla();
    }


    // métodos de la UI
    @FXML
    public void irTablaOrganizaciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaOrganizaciones.fxml");
    }

    @FXML
    public void irInicio(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/CoordInicio.fxml");
    }

    @FXML
    public void irTablaProyectos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaProyectos.fxml");
    }

    @FXML
    void modalAgregarPracticante(ActionEvent event) {
        Stage stageActual = (Stage) agregarPracticante.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/coord/ModalRegistrarPrac.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) agregarPracticante.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    // métodos
    public void popularTabla(){
        practicantes = pracDao.obtenerPracticantes();
        tablaPracticantes.getItems().setAll(practicantes);
    }
}
