package practicante;

import DataAccess.DAO.ReporteMensualDAO;
import DataAccess.DAO.ReporteParcialDAO;
import Dominio.ReporteMensual;
import Dominio.ReporteParcial;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import profesor.TablaReportesParcialesController;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaReportesController implements Initializable {
    @FXML
    private TextField tfHoras;
    @FXML
    private TextArea taActividades;
    @FXML
    private TableView<ReporteMensual> tablaReportes;
    @FXML
    private TableColumn<ReporteMensual, String> colTipo;
    @FXML
    private TableColumn<ReporteMensual, String> colFecha;
    @FXML
    private TableColumn<ReporteMensual, Integer> colHoras;
    @FXML
    private TableColumn<ReporteMensual, String> colEvaluacion;
    Stage stage;
    Scene scene;
    SceneSwitcher sw = new SceneSwitcher();
    @FXML
    private Label inicio;
    ReporteMensualDAO repMensualDao = new ReporteMensualDAO();
    ReporteParcialDAO repParcialDao = new ReporteParcialDAO();
    ArrayList<ReporteMensual> reportesMensuales = new ArrayList<>();
    ArrayList<ReporteParcial> reportesParciales = new ArrayList<>();
    String matricula = Usuario.usuarioActual.getMatricula();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colHoras.setCellValueFactory(new PropertyValueFactory<ReporteMensual, Integer>("horas"));
        colTipo.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("tipo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("fecha"));
        colEvaluacion.setCellValueFactory(new PropertyValueFactory<ReporteMensual, String>("evaluacion"));

        popularTabla();
    }

    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaReportesController instance;

    public TablaReportesController(){
        instance = this;
    }

    public static TablaReportesController getInstance(){
        return instance;
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

    public void irInicio(MouseEvent event) throws IOException{
        sw.switchSceneMouse(event, stage, scene, "../practicante/PracticanteInicio.fxml");
    }

    public void modalReporte(MouseEvent event) {
    }

    public void generarReporteMensual(ActionEvent event) {
        Stage stageActual = (Stage) tablaReportes.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/practicante/ModalGenerarRepMensual.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void generarReporteParcial(ActionEvent event) {
        Stage stageActual = (Stage) tablaReportes.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/practicante/ModalGenerarRepParcial.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void popularTabla() {
        reportesMensuales = null;
        reportesParciales = null;
        reportesParciales = repParcialDao.obtenerReportesParcialesPorMat(matricula);
        reportesMensuales = repMensualDao.obtenerReportesMensualesPorMat(matricula);
        reportesParciales.forEach(reporteParcial -> {
            ReporteMensual reporteMensual = new ReporteMensual();
            reporteMensual.setTipo(reporteParcial.getTipo());
            reporteMensual.setId(reporteParcial.getId());
            reporteMensual.setHoras(reporteParcial.getHoras());
            reporteMensual.setActividades(reporteParcial.getActividades());
            reporteMensual.setFecha(reporteParcial.getFecha());

            reportesMensuales.add(reporteMensual);
        });

        tablaReportes.getItems().setAll(reportesMensuales);
    }


}

