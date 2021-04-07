package practicante;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;

import java.io.IOException;

public class PracticanteInicioController {
    private Stage stage;
    private Scene scene;
    SceneSwitcher sw = new SceneSwitcher();

    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    public void irOpciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }
}
