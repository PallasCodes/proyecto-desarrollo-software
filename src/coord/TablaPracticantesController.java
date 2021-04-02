package coord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;

import java.io.IOException;

public class TablaPracticantesController {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();


    // componentes de la UI
    @FXML
    private Button agregarPracticante;
    @FXML
    private TableView<?> tablaProyectos;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colPrimerApe;
    @FXML
    private TableColumn<?, ?> colSegundoApe;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colProyecto;
    @FXML
    private TableColumn<?, ?> colPeriodo;
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
}
