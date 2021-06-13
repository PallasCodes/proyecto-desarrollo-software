package profesor;

import DataAccess.DAO.AutoevaluacionDAO;
import Dominio.Autoevaluacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalAutoevaluacionController implements Initializable {
    @FXML
    private Label respuesta1;
    @FXML
    private Label respuesta2;
    @FXML
    private Label respuesta3;
    @FXML
    private Label respuesta4;
    @FXML
    private Label respuesta5;
    @FXML
    private Label respuesta6;
    @FXML
    private Label respuesta7;
    @FXML
    private Label respuesta8;
    @FXML
    private Label respuesta9;
    @FXML
    private Label practicante;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Autoevaluacion autoevaluacion = Autoevaluacion.autoevaluacionSeleccionada;

        respuesta1.setText(Integer.toString(autoevaluacion.getRespuesta1()));
        respuesta2.setText(Integer.toString(autoevaluacion.getRespuesta2()));
        respuesta3.setText(Integer.toString(autoevaluacion.getRespuesta3()));
        respuesta4.setText(Integer.toString(autoevaluacion.getRespuesta4()));
        respuesta5.setText(Integer.toString(autoevaluacion.getRespuesta5()));
        respuesta6.setText(Integer.toString(autoevaluacion.getRespuesta6()));
        respuesta7.setText(Integer.toString(autoevaluacion.getRespuesta7()));
        respuesta8.setText(Integer.toString(autoevaluacion.getRespuesta8()));
        respuesta9.setText(Integer.toString(autoevaluacion.getRespuesta9()));

        practicante.setText(autoevaluacion.getMatricula());
    }
}
