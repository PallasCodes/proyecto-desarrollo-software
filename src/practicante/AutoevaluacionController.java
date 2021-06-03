package practicante;

import DataAccess.DAO.AutoevaluacionDAO;
import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.Autoevaluacion;
import Dominio.Proyecto;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AutoevaluacionController implements Initializable {
    // instancias de las clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    ProyectoDAO proyectoDao = new ProyectoDAO();
    PracticanteDAO pracDao = new PracticanteDAO();
    AutoevaluacionDAO autoDao = new AutoevaluacionDAO();
    ArrayList<Proyecto> proyectos;
    ArrayList<Proyecto> proyectosSeleccionados = new ArrayList<>();
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private Label inicio;
    @FXML
    private Label lbError;
    @FXML
    private TextField respuesta1;
    @FXML
    private TextField respuesta2;
    @FXML
    private TextField respuesta3;
    @FXML
    private TextField respuesta4;
    @FXML
    private TextField respuesta5;
    @FXML
    private TextField respuesta6;
    @FXML
    private TextField respuesta7;
    @FXML
    private TextField respuesta8;
    @FXML
    private TextField respuesta9;
    private Stage stage;
    private Scene scene;


    // inicializar vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void irTablaReportes(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/practicante/TablaReportes.fxml");
    }

    public void irPerfil(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "/practicante/PracticanteInicio.fxml");
    }

    public void cancelar(ActionEvent event) throws IOException{
        sw.switchScene(event, stage, scene, "../practicante/PracticanteInicio.fxml");
    }

    public void enviar(ActionEvent event) throws IOException{
        if(camposCompletos()){
            if(datosValidos()){
                Autoevaluacion autoevaluacion = generarAutoevaluacion();
                lbError.setText((""));
                if(autoDao.enviarAutoevaluacion(autoevaluacion, Usuario.usuarioActual.getMatricula())){
                    sw.switchScene(event, stage, scene, "/practicante/PracticanteInicio.fxml");
                };
            } else {
                lbError.setText("*Introduzca solo números del 1 al 5");
            }
        } else {
            lbError.setText("*Responda todas las preguntas de la autoevaluaicón");
        }
    }

    private Autoevaluacion generarAutoevaluacion() {
        Autoevaluacion autoevaluacion = new Autoevaluacion();
        autoevaluacion.setRespuesta1(Integer.parseInt(respuesta1.getText()));
        autoevaluacion.setRespuesta2(Integer.parseInt(respuesta2.getText()));
        autoevaluacion.setRespuesta3(Integer.parseInt(respuesta3.getText()));
        autoevaluacion.setRespuesta4(Integer.parseInt(respuesta4.getText()));
        autoevaluacion.setRespuesta5(Integer.parseInt(respuesta5.getText()));
        autoevaluacion.setRespuesta6(Integer.parseInt(respuesta6.getText()));
        autoevaluacion.setRespuesta7(Integer.parseInt(respuesta7.getText()));
        autoevaluacion.setRespuesta8(Integer.parseInt(respuesta8.getText()));
        autoevaluacion.setRespuesta9(Integer.parseInt(respuesta9.getText()));

        return autoevaluacion;
    }

    private boolean datosValidos(){
        ArrayList<Integer> respuestas = new ArrayList();
        try{
            respuestas.add(Integer.parseInt(respuesta1.getText()));
            respuestas.add(Integer.parseInt(respuesta2.getText()));
            respuestas.add(Integer.parseInt(respuesta3.getText()));
            respuestas.add(Integer.parseInt(respuesta4.getText()));
            respuestas.add(Integer.parseInt(respuesta5.getText()));
            respuestas.add(Integer.parseInt(respuesta6.getText()));
            respuestas.add(Integer.parseInt(respuesta7.getText()));
            respuestas.add(Integer.parseInt(respuesta8.getText()));
            respuestas.add(Integer.parseInt(respuesta9.getText()));

            for (Integer respuesta : respuestas) {
                if (respuesta > 5 || respuesta < 1)
                    return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean camposCompletos(){
        return !respuesta1.getText().equals("") && !respuesta2.getText().equals("") && !respuesta3.getText().equals("") && !respuesta4.getText().equals("")
                && !respuesta5.getText().equals("") && !respuesta6.getText().equals("") && !respuesta7.getText().equals("") && !respuesta8.getText().equals("")
                && !respuesta9.getText().equals("");
    }

}
