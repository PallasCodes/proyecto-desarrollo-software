package profesor;

import DataAccess.DAO.AutoevaluacionDAO;
import DataAccess.DAO.ReporteParcialDAO;
import Dominio.Autoevaluacion;
import Dominio.ReporteMensual;
import Dominio.ReporteParcial;
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

public class TablaAutoevaluacionesController implements Initializable {
    ArrayList<Autoevaluacion> autoevaluaciones;
    AutoevaluacionDAO autoevaluacionDao = new AutoevaluacionDAO();

    @FXML
    private Label inicio;
    @FXML
    private TableView<Autoevaluacion> tablaAutoevaluacion;
    @FXML
    private TableColumn<Autoevaluacion, String> colMatricula;
    @FXML
    private TableColumn<Autoevaluacion, String> colFecha;
    @FXML
    Stage stage;
    Scene scene;
    SceneSwitcher sw = new SceneSwitcher();


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMatricula.setCellValueFactory(new PropertyValueFactory<Autoevaluacion, String>("matricula"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Autoevaluacion, String>("fecha"));

        popularTabla();
    }

    //  métodos de la UI
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) tablaAutoevaluacion.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void modalAutoevaluacion(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Stage stageActual = (Stage) tablaAutoevaluacion.getScene().getWindow();
            try {
                ReporteParcial.reporteSeleccionado = obtenerReporteSeleccionado();
                sw.createDialog(stageActual, "/profesor/ModalAutoevaluacion.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public ReporteParcial obtenerReporteSeleccionado(){
        Autoevaluacion.autoevaluacionSeleccionada = tablaAutoevaluacion.getSelectionModel().getSelectedItem();
        return ReporteParcial.reporteSeleccionado;
    }

    public void popularTabla() {
        autoevaluaciones = autoevaluacionDao.obtenerAutoevaluaciones(Usuario.usuarioActual.getMatricula());
        tablaAutoevaluacion.getItems().setAll(autoevaluaciones);
    }

    public void irPerfil(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/ProfesorInicio.fxml");
    }

    public void irTablaReportesMensuales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesMensuales.fxml");
    }

    public void irTablaAlumnos(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaAlumnos.fxml");
    }
}
