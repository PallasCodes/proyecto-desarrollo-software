package coord;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.SolicitudesDAO;
import Dominio.Practicante;
import Dominio.Solicitud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.AlertBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModalSolicitudController implements Initializable {
    // instancias de clases usadas
    SolicitudesDAO solicitudesDao = new SolicitudesDAO();
    ArrayList<Solicitud> solicitudes;
    Practicante practicante = Practicante.practicanteSeleccionado;
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private Label labelError;
    @FXML
    private Text matricula;
    @FXML
    private Text nombre;
    @FXML
    private Text opcion1;
    @FXML
    private Button btnAsignar1;
    @FXML
    private Text opcion2;
    @FXML
    private Button btnAsignar2;
    @FXML
    private Text opcion3;
    @FXML
    private Button btnAsignar3;
    @FXML
    private Button btnAsignarOtro;
    @FXML
    private Button btnCancelar;


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // mostrar datos practicante
        nombre.setText(practicante.getNombre()+" "+practicante.getPrimerApellido() + " "+practicante.getSegundoApellido());
        matricula.setText(practicante.getMatricula());

        // mostrar solicitudes
        solicitudes = solicitudesDao.obtenerSolicitudesPracticante(practicante.getMatricula());

        Solicitud solicitud1 = solicitudes.get(0);
        opcion1.setText(solicitud1.getProyecto() + " - " + solicitud1.getOrganizacion() + "\n" +
                "Disponibilidad: " + solicitud1.getDisponibilidad());

        if(solicitudes.size() > 1){
            Solicitud solicitud2 = solicitudes.get(1);
            opcion2.setText(solicitud2.getProyecto() + " - " + solicitud2.getOrganizacion() + "\n" +
                    "Disponibilidad: " + solicitud2.getDisponibilidad());

            btnAsignar2.setDisable(false);
        }

        if(solicitudes.size() > 2){
            Solicitud solicitud3 = solicitudes.get(2);
            opcion3.setText(solicitud3.getProyecto() + " - " + solicitud3.getOrganizacion() + "\n" +
                    "Disponibilidad: " + solicitud3.getDisponibilidad());

            btnAsignar3.setDisable(false);
        }
    }


    // métodos de la UI
    @FXML
    void asignar1(ActionEvent event) {
        asignarProyecto(solicitudes.get(0).getProyectoId());
    }

    @FXML
    void asignar2(ActionEvent event) {
        asignarProyecto(solicitudes.get(1).getProyectoId());
    }

    @FXML
    void asignar3(ActionEvent event) {
        asignarProyecto(solicitudes.get(2).getProyectoId());
    }

    @FXML
    void asignarOtro(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    void asignarProyecto(int proyectoId){
        if(solicitudesDao.asignarProyecto(proyectoId, practicante.getMatricula())){
            TablaSolicitudesController.getInstance().popularTabla();
            solicitudesDao.disminuirDisponibilidadProyecto(proyectoId);
            solicitudesDao.eliminarSolicitudes(practicante.getMatricula());
            alert.successAlert("Proyecto asignado con éxito");
        }
        Stage stage = (Stage) btnAsignar1.getScene().getWindow();
        stage.close();
        Practicante.practicanteSeleccionado = null;
    }
}
