package profesor;

import DataAccess.DAO.ReporteParcialDAO;
import Dominio.ReporteMensual;
import Dominio.ReporteParcial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ModalReporteParcialController implements Initializable {
    // instancias de clases usadas
    ReporteParcial reporte = ReporteParcial.reporteSeleccionado;
    ReporteParcialDAO reporteDao = new ReporteParcialDAO();


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
        int id = ReporteParcial.reporteSeleccionado.getId();
        ReporteParcial detalles = reporteDao.obtenerDetalles(id);

        organizacion.setText(detalles.getOrganizacion());
        proyecto.setText(detalles.getProyecto());
        practicante.setText(detalles.getMatricula());
        fecha.setText(reporte.getFecha());
        actividades.setText(reporte.getActividades());
        cbEvaluacion.getItems().setAll("Aceptado","Rechazado");
    }

    // métodos de la UI
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void evaluar(ActionEvent event) {
        if(cbEvaluacion.getSelectionModel().isEmpty()){
            labelError.setText("Seleccione una evaluación");
        } else {
            if(reporteDao.evaluarReporteParcial(cbEvaluacion.getValue(),reporte.getId())) {
                TablaReportesParcialesController.getInstance().popularTabla();
                ReporteParcial.reporteSeleccionado = null;
            }
            Stage stage = (Stage) btnEvaluar.getScene().getWindow();
            stage.close();
        }
    }
}
