package practicante;

import DataAccess.DAO.ArchivoDAO;
import Dominio.Archivo;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubirArchivoController implements Initializable {

    @FXML
    TableView archivosTabla;
    @FXML
    TableColumn tipoArchivoColumna;
    @FXML
    TableColumn nombreArchivoColumna;

    Stage stage;
    Scene scene;

    private static SubirArchivoController instance;

    public SubirArchivoController() {
        instance = this;
    }

    public static SubirArchivoController getInstance() {
        return instance;
    }

    ArrayList<Archivo> todosLosArchivos = new ArrayList<>();
    ArchivoDAO archivoDAO = new ArchivoDAO();
    AlertBuilder alertBuilder = new AlertBuilder();
    SceneSwitcher sw = new SceneSwitcher();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    tipoArchivoColumna.setCellValueFactory(new PropertyValueFactory<Archivo, String>("tipoArchivo"));
    nombreArchivoColumna.setCellValueFactory(new PropertyValueFactory<Archivo, String>("nombreArchivo"));
    popularTabla();
    }

    public void popularTabla() {
        try {
            todosLosArchivos = archivoDAO.obtenerArchivos();
        } catch (SQLException sqlException) {
            alertBuilder.exceptionAlert("No es posible acceder a la base de datos. Intente más tarde.");
        }
        archivosTabla.getItems().setAll(todosLosArchivos);
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void subirArchivo() {
        Stage stageActual = (Stage) archivosTabla.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/practicante/ModalSubirArchivo.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) archivosTabla.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
