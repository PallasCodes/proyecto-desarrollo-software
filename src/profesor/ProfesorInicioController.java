package profesor;

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

public class ProfesorInicioController implements Initializable {
    // instancias de las clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    UsuarioDAO usuarioDao = new UsuarioDAO();


    // componentes de la UI
    @FXML
    private Label inicio;
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
    private Stage stage;
    private Scene scene;


    // inicializaicón de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuario = Usuario.usuarioActual;

        lbNombre.setText(usuario.getNombre()+ " " + usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
        lbRol.setText(usuario.getRol());
        lbFacultad.setText(usuario.getFacultad());
        lbCorreo.setText(usuario.getCorreo());
        lbTelefono.setText(usuario.getTelefono());
    }


    //  métodos de la UI
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

    public void irTablaReportesMensuales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesMensuales.fxml");
    }

    public void irTablaReportesParciales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesParciales.fxml");
    }

    public void irTablaAlumnos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaAlumnos.fxml");
    }

    public void irTablaAutoevaluaciones(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaAutoevaluaciones.fxml");
    }
}
