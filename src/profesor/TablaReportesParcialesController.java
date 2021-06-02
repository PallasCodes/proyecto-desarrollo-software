package profesor;

import DataAccess.DAO.ReporteParcialDAO;
import Dominio.ReporteMensual;
import Dominio.ReporteParcial;
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

public class TablaReportesParcialesController implements Initializable {
    ArrayList<ReporteParcial> reportes;
    ReporteParcialDAO reporteParcialDao = new ReporteParcialDAO();

    @FXML
    private Label inicio;
    @FXML
    private TableView<ReporteParcial> tablaReportesParciales;
    @FXML
    private TableColumn<ReporteParcial, String> colMatricula;
    @FXML
    private TableColumn<ReporteParcial, String> colFecha;
    @FXML
    private TableColumn<ReporteParcial, String> colEvaluacion;
    Stage stage;
    Scene scene;
    SceneSwitcher sw = new SceneSwitcher();


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaReportesParcialesController instance;

    public TablaReportesParcialesController(){
        instance = this;
    }

    public static TablaReportesParcialesController getInstance(){
        return instance;
    }


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMatricula.setCellValueFactory(new PropertyValueFactory<ReporteParcial, String>("matricula"));
        colFecha.setCellValueFactory(new PropertyValueFactory<ReporteParcial, String>("fecha"));
        colEvaluacion.setCellValueFactory(new PropertyValueFactory<ReporteParcial, String>("evaluacion"));

        popularTabla();
    }

    //  métodos de la UI
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) tablaReportesParciales.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void modalReporte(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Stage stageActual = (Stage) tablaReportesParciales.getScene().getWindow();
            try {
                ReporteParcial.reporteSeleccionado = obtenerReporteSeleccionado();
                sw.createDialog(stageActual, "/profesor/ModalReporteParcial.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public ReporteParcial obtenerReporteSeleccionado(){
        ReporteParcial.reporteSeleccionado = tablaReportesParciales.getSelectionModel().getSelectedItem();
        return ReporteParcial.reporteSeleccionado;
    }

    public void popularTabla() {
        reportes = reporteParcialDao.obtenerReportesParciales();
        tablaReportesParciales.getItems().setAll(reportes);
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
