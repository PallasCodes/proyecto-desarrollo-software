package admin;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;
import java.io.IOException;

public class AdminInicioController {
    @FXML
    private Label inicio;
    private Stage stage;
    private Scene scene;
    SceneSwitcher sw = new SceneSwitcher();

    public void irTablaCoordinadores(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../admin/TablaCoordinadores.fxml");
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
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
}
