package practicante;

import DataAccess.DAO.ReporteMensualDAO;
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

public class ModalGenerarRepMensualController implements Initializable {
    @FXML
    private Label lbError;
    @FXML
    private TextField tfHoras;
    @FXML
    private TextArea taActividades;
    ReporteMensualDAO repDao = new ReporteMensualDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) lbError.getScene().getWindow();
        stage.close();
    }

    public void generarReporte(ActionEvent event) {
        if(camposCompletos()){
            if(datosValidos()){
                lbError.setText("");
                String matricula = Usuario.usuarioActual.getMatricula();
                int horas = Integer.parseInt(tfHoras.getText());
                String actividades = taActividades.getText();
                if(repDao.generarReporte(matricula, horas, actividades)){
                    TablaReportesController.getInstance().popularTabla();
                };
                Stage stage = (Stage) lbError.getScene().getWindow();
                stage.close();
            } else {
                lbError.setText("*Introduzca un número valido en Horas Completadas");
            }
        } else {
            lbError.setText("*Llene todos los campos del formulario");
        }
    }


    private boolean datosValidos() {
        int horas = 0;
        try {
            horas = Integer.parseInt(tfHoras.getText());
        } catch (Exception e) {
            return false;
        }
        return horas <= 100 && horas >= 30;
    }

    private boolean camposCompletos() {
        return !tfHoras.getText().equals("") && !taActividades.getText().equals("");
    }
}
