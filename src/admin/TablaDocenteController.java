package admin;

import DataAccess.DAO.DocenteDAO;
import Dominio.Proyecto;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaDocenteController implements Initializable {

    SceneSwitcher sw = new SceneSwitcher();
    ArrayList<Usuario> docentes;
    DocenteDAO docenteDAO = new DocenteDAO();
    AlertBuilder alert = new AlertBuilder();



    @FXML
    private Button agregarDocente;
    @FXML
    private TableView<Usuario> tablaDocentes;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colPrimerApe;
    @FXML
    private TableColumn<Usuario, String> colSegundoApe;
    @FXML
    private TableColumn<Usuario, String> colId;
    @FXML
    private TableColumn<Usuario, String> colFacultad;

    Stage stage;
    Scene scene;

    private static TablaDocenteController instance;

    public TablaDocenteController(){
        instance = this;
    }

    public static TablaDocenteController getInstance(){
        return instance;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        colPrimerApe.setCellValueFactory(new PropertyValueFactory<Usuario, String>("primerApellido"));
        colSegundoApe.setCellValueFactory(new PropertyValueFactory<Usuario, String>("segundoApellido"));
        colFacultad.setCellValueFactory(new PropertyValueFactory<Usuario, String>("facultad"));
        colId.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuarioId"));
        popularTabla();
    }


    // métodos de la UI
    @FXML
    public void irInicio(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../admin/AdminInicio.fxml");
    }

    @FXML
    public void irTablaCoordinadores(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../admin/TablaCoordinadores.fxml");
    }


    @FXML
    void modalAgregarDocente(ActionEvent event) {
        Stage stageActual = (Stage) agregarDocente.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/admin/ModalRegistrarDocente.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../login/login.fxml");
    }

    @FXML
    public void irOpciones(MouseEvent event) throws IOException {
        Stage stageActual = (Stage) agregarDocente.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    // métodos
    public void popularTabla(){
        docentes = docenteDAO.obtenerDocentes();
        tablaDocentes.getItems().setAll(docentes);
    }

    public void actualizarDocente(ActionEvent actionEvent) {
        Usuario docente = obtenerDocenteSeleccionado();
        if(docente == null){
            alert.errorAlert("Seleccione un docente.");
        }else {
            SceneSwitcher switcher = new SceneSwitcher();
            Stage stageActual = (Stage) agregarDocente.getScene().getWindow();
            try {
                switcher.createDialog(stageActual, "/admin/ModalActualizarDocente.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void eliminarDocente(ActionEvent event) {
        Usuario docenteSeleccionado = obtenerDocenteSeleccionado();
        if(docenteSeleccionado == null){
            alert.errorAlert("Seleccione un docente.");
        }else if(alert.confirmationAlert("¿Está seguro de eliminar al docente?")){
            String numeroPersonal = Usuario.docenteSeleccionado.getMatricula();
            if(docenteDAO.eliminarDocente(numeroPersonal)) {
                Proyecto.proyectoSeleccionado = null;
                popularTabla();
            }
        }
    }

    public Usuario obtenerDocenteSeleccionado(){
        Usuario.docenteSeleccionado = tablaDocentes.getSelectionModel().getSelectedItem();
        return Usuario.docenteSeleccionado;
    }

}