package coord;

import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoordInicioController implements Initializable {
    // instancias de las clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    UsuarioDAO usuarioDao = new UsuarioDAO();


    // componentes de la UI
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbRol;
    @FXML
    private Label lbFacultad;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label inicio;
    private Stage stage;
    private Scene scene;


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuario = Usuario.usuarioActual;

        lbNombre.setText(usuario.getNombre()+ " " + usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
        lbRol.setText(usuario.getRol());
        lbFacultad.setText(usuario.getFacultad());
        lbCorreo.setText(usuario.getCorreo());
        lbTelefono.setText(usuario.getTelefono());
    }


    // métodos de la UI
    public void irTablaProyectos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaProyectos.fxml");
    }

    public void irTablaOrganizaciones(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaOrganizaciones.fxml");
    }

    public void irTablaPracticantes(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaPracticantes.fxml");
    }

    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) inicio.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void irTablaSolicitudes(MouseEvent event)  throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../coord/TablaSolicitudes.fxml");
    }
}
