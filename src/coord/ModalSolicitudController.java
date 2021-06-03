package coord;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.ProyectoDAO;
import DataAccess.DAO.SolicitudesDAO;
import Dominio.Practicante;
import Dominio.Proyecto;
import Dominio.Solicitud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    ArrayList<Proyecto> proyectos;
    ProyectoDAO proyectoDao = new ProyectoDAO();
    boolean asignarOtro = false;


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
    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> colNombre;
    @FXML
    private TableColumn<Proyecto, String> colOrganizacion;
    @FXML
    private TableColumn<Proyecto, Integer> colDisponibilidad;
    @FXML
    private Accordion opciones;


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

        colNombre.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("nombre"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory<Proyecto, String>("organizacion"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Proyecto, Integer>("disponibilidad"));

        popularTabla();
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
        if(!asignarOtro){
            asignarOtro = true;
            tablaProyectos.setDisable(false);
            tablaProyectos.setVisible(true);
            opciones.setDisable(true);
            opciones.setVisible(false);
            btnAsignarOtro.setText("Asignar");
        } else {
            Proyecto proyecto = obtenerProSeleccionado();
            if (proyecto != null) {
                asignarProyecto(proyecto.getId());
            } else {
                alert.errorAlert("Selecciona un proyecto");
            }
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) tablaProyectos.getScene().getWindow();
        stage.close();
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

    public void popularTabla() {
        proyectos = proyectoDao.obtenerProyectos();
        tablaProyectos.getItems().setAll(proyectos);
    }


    public Proyecto obtenerProSeleccionado(){
        Proyecto.proyectoSeleccionado = tablaProyectos.getSelectionModel().getSelectedItem();
        return Proyecto.proyectoSeleccionado;
    }
}
