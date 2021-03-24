package coord;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;

import java.io.IOException;

public class CoordInicioController {
    private Stage stage;
    private Scene scene;
    SceneSwitcher sw = new SceneSwitcher();

    public void irTablaProyectos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaProyectos.fxml");
    }

    public void irTablaOrganizaciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaOrganizaciones.fxml");
    }
}
