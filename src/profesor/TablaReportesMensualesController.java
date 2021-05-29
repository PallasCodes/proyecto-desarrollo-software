package profesor;

import DataAccess.DAO.ReporteMensualDAO;
import Dominio.ReporteMensual;
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

public class TablaReportesMensualesController implements Initializable {
    Stage stage;
    Scene scene;
    SceneSwitcher sw = new SceneSwitcher();
    ArrayList<ReporteMensual> reportes;
    ReporteMensualDAO reporteMensualDao = new ReporteMensualDAO();


    @FXML
    private Label inicio;
    @FXML
    private TableView<ReporteMensual> tablaReportesMensuales;
    @FXML
    private TableColumn<ReporteMensual, String> colMatricula;
    @FXML
    private TableColumn<ReporteMensual, String> colFecha;
    @FXML
    private TableColumn<ReporteMensual, String> colEstado;
    @FXML
    private TableColumn<ReporteMensual, Integer> colHoras;
    @FXML
    private TableColumn<ReporteMensual, String> colEvaluacion;


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaReportesMensualesController instance;

    public TablaReportesMensualesController(){
        instance = this;
    }

    public static TablaReportesMensualesController getInstance(){
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMatricula.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("matricula"));
        colFecha.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("estado"));
        colHoras.setCellValueFactory(new PropertyValueFactory<ReporteMensual, Integer>("horas"));
        colEvaluacion.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("evaluacion"));

        popularTabla();
    }

    public void modalReporte(MouseEvent event) {
        if(event.getClickCount() >= 2){
            Stage stageActual = (Stage) tablaReportesMensuales.getScene().getWindow();
            try {
                ReporteMensual.reporteSeleccionado = obtenerReporteSeleccionado();
                sw.createDialog(stageActual, "/profesor/ModalReporteMensual.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //  métodos de la UI
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/login/Login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) tablaReportesMensuales.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void popularTabla() {
        reportes = reporteMensualDao.obtenerReportesMensuales();
        tablaReportesMensuales.getItems().setAll(reportes);
    }

    public ReporteMensual obtenerReporteSeleccionado(){
        ReporteMensual.reporteSeleccionado = tablaReportesMensuales.getSelectionModel().getSelectedItem();
        return ReporteMensual.reporteSeleccionado;
    }

    public void irPerfil(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/ProfesorInicio.fxml");
    }

    public void irTablaReportesParciales(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../profesor/TablaReportesParciales.fxml");
    }
}
