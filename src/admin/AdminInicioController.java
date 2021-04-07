package admin;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;

import java.io.IOException;

public class AdminInicioController {
    private Stage stage;
    private Scene scene;
    SceneSwitcher sw = new SceneSwitcher();

    public void irTablaCoordinadores(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../admin/TablaCoordinadores.fxml");
    }
}
