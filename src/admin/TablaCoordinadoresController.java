package admin;

import DataAccess.DAO.CoordinadorDAO;
import DataAccess.DAO.PracticanteDAO;
import Dominio.Practicante;
import Dominio.Proyecto;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TablaCoordinadoresController implements Initializable {
    // instancias de clases usadas
    SceneSwitcher sw = new SceneSwitcher();
    ArrayList<Usuario> coordinadores;
    PracticanteDAO pracDao = new PracticanteDAO();
    CoordinadorDAO coordDao = new CoordinadorDAO();
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private Button agregarCoordinador;
    @FXML
    private TableView<Usuario> tablaCoordinadores;
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


    // instancia del controlador usada para acceder a sus métodos desde otros controladores
    private static TablaCoordinadoresController instance;

    public TablaCoordinadoresController(){
        instance = this;
    }

    public static TablaCoordinadoresController getInstance(){
        return instance;
    }


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        colPrimerApe.setCellValueFactory(new PropertyValueFactory<Usuario, String>("PrimerApe"));
        colSegundoApe.setCellValueFactory(new PropertyValueFactory<Usuario, String>("SegundoApe"));
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
    void modalAgregarCoordinador(ActionEvent event) {
        Stage stageActual = (Stage) agregarCoordinador.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/admin/ModalRegistrarCoord.fxml");
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
        Stage stageActual = (Stage) agregarCoordinador.getScene().getWindow();
        try {
            sw.createDialog(stageActual, "/login/ModalCambiarContraseña.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    // métodos
    public void popularTabla(){
        coordinadores = coordDao.obtenerCoordinadores();
        tablaCoordinadores.getItems().setAll(coordinadores);
    }

    public void actualizarCoord(ActionEvent actionEvent) {
        Usuario coord = obtenerCoordSeleccionado();
        if(coord == null){
            alert.errorAlert("Error. Seleccione un coordinador");
        }else {
            SceneSwitcher switcher = new SceneSwitcher();
            Stage stageActual = (Stage) agregarCoordinador.getScene().getWindow();
            try {
                switcher.createDialog(stageActual, "/admin/ModalActualizarCoord.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void eliminarCoord(ActionEvent event) {
        Usuario coordinador = obtenerCoordSeleccionado();
        if(coordinador == null){
            alert.errorAlert("Error. Seleccione un coordinador");
        }else if(alert.confirmationAlert("¿Desea eliminar el coordinador?")){
            String matricula = Usuario.coordinadorSeleccionado.getMatricula();
            if(coordDao.eliminarCoordinador(matricula)) {
                Proyecto.proyectoSeleccionado = null;
                popularTabla();
                // si sucede una excepción, no se actualiza la tabla
            }
        }
    }

    public Usuario obtenerCoordSeleccionado(){
        Usuario.coordinadorSeleccionado = tablaCoordinadores.getSelectionModel().getSelectedItem();
        return Usuario.coordinadorSeleccionado;
    }
}
