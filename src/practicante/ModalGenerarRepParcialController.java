package practicante;

import DataAccess.DAO.ReporteMensualDAO;
import DataAccess.DAO.ReporteParcialDAO;
import Dominio.ReporteParcial;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalGenerarRepParcialController implements Initializable {
    @FXML
    private Label lbError;
    @FXML
    private TextArea taActividades;
    ReporteParcialDAO repDao = new ReporteParcialDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) lbError.getScene().getWindow();
        stage.close();
    }

    public void generarReporte(ActionEvent event) {
        if(camposCompletos()){
            lbError.setText("");
            String matricula = Usuario.usuarioActual.getMatricula();
            String actividades = taActividades.getText();
            if(repDao.generarReporteParcial(matricula, actividades)){
                TablaReportesController.getInstance().popularTabla();
            };
            Stage stage = (Stage) lbError.getScene().getWindow();
            stage.close();

        } else {
            lbError.setText("*Llene todos los campos del formulario");
        }
    }


    private boolean camposCompletos() {
        return !taActividades.getText().equals("");
    }
}
