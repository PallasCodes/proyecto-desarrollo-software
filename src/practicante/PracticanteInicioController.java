package practicante;

import DataAccess.DAO.AutoevaluacionDAO;
import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Practicante;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PracticanteInicioController implements Initializable {
    // instancias de las clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    PracticanteDAO pracDao = new PracticanteDAO();
    AutoevaluacionDAO autoDao = new AutoevaluacionDAO();

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
    @FXML
    private Button btnSolicitarProyecto;
    @FXML
    private Button btnAutoevaluacion;
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

        if(!pracDao.obtenerEstadoPracticante(usuario.getMatricula()).equals("sin asignar")){
            btnSolicitarProyecto.setDisable(true);
            btnSolicitarProyecto.setVisible(false);
        }

        boolean realizoAutoevaluacion = autoDao.realizoAutoevaluacion(Usuario.usuarioActual.getMatricula());

        if(pracDao.obtenerHoras(usuario.getMatricula()) < 400 || realizoAutoevaluacion){
            btnAutoevaluacion.setDisable(true);
            btnAutoevaluacion.setVisible(false);
        }
    }


    // métodos de la UI
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

    public void irSolicitarProyecto(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "/practicante/SolicitarProyecto.fxml");
    }

    public void irArchivos(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "/practicante/SubirArchivo.fxml");
    }

    public void irTablaReportes(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/practicante/TablaReportes.fxml");
    }

    public void irAutoevaluacion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/practicante/Autoevaluacion.fxml");
    }
}
