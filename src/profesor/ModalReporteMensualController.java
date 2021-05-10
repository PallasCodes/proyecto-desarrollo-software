package profesor;

import DataAccess.DAO.ReporteMensualDAO;
import Dominio.ReporteMensual;
import coord.TablaProyectosController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModalReporteMensualController implements Initializable {
    // instancias de clases usadas
    ReporteMensual reporte = ReporteMensual.reporteSeleccionado;
    ReporteMensualDAO reporteDao = new ReporteMensualDAO();


    // componentes de la UI
    @FXML
    private Label labelError;
    @FXML
    private Text organizacion;
    @FXML
    private Text practicante;
    @FXML
    private Button btnCancelar;
    @FXML
    private Text proyecto;
    @FXML
    private Text fecha;
    @FXML
    private Text horas;
    @FXML
    private Text actividades;
    @FXML
    private ComboBox<String> cbEvaluacion;
    @FXML
    private Button btnEvaluar;


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        organizacion.setText("uwu");
        proyecto.setText("uwu");
        practicante.setText("owo");
        fecha.setText(reporte.getFecha());
        horas.setText(Integer.toString(reporte.getHoras()));
        actividades.setText(reporte.getActividades());
        cbEvaluacion.getItems().setAll("Aceptado","Rechazado");
    }


    // métodos de la UI
    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void evaluar(ActionEvent event) {
        if(cbEvaluacion.getSelectionModel().isEmpty()){
            labelError.setText("Seleccione una evaluación");
        } else {
            if(reporteDao.evaluarReporteMensual(cbEvaluacion.getValue(),reporte.getId())) {
                TablaReportesMensualesController.getInstance().popularTabla();
                ReporteMensual.reporteSeleccionado = null;
            }
            Stage stage = (Stage) btnEvaluar.getScene().getWindow();
            stage.close();
        }
    }
}
