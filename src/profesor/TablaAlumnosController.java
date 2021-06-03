package profesor;

import DataAccess.DAO.PracticanteDAO;
import Dominio.Practicante;
import Dominio.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaAlumnosController implements Initializable {
    Stage stage;
    Scene scene;
    SceneSwitcher sw = new SceneSwitcher();
    ArrayList<Practicante> alumnos = new ArrayList<>();
    PracticanteDAO pracDao = new PracticanteDAO();


    @FXML
    private Label inicio;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Practicante, String>("nombre"));
        colPrimerApe.setCellValueFactory(new PropertyValueFactory<Practicante, String>("PrimerApellido"));
        colSegundoApe.setCellValueFactory(new PropertyValueFactory<Practicante, String>("SegundoApellido"));
        colProyecto.setCellValueFactory(new PropertyValueFactory<Practicante, String>("proyecto"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<Practicante, String>("periodo"));

        popularTabla();
    }

    //  métodos de la UI
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) tablaPracticantes.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void popularTabla() {
        alumnos = pracDao.obtenerAlumnos(Usuario.usuarioActual.getMatricula());
        tablaPracticantes.getItems().setAll(alumnos);
    }

    public void irPerfil(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/ProfesorInicio.fxml");
    }

    public void irTablaReportesParciales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesParciales.fxml");
    }

    public void irTablaReportesMensuales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesMensuales.fxml");
    }

    public void irTablaAutoevaluaciones(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaAutoevaluaciones.fxml");
    }
}
