package coord;

import DataAccess.DAO.OrganizacionVinculadaDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.OrganizacionVinculada;
import Dominio.Proyecto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.AlertBuilder;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModalActualizarProController implements Initializable {
    // instancias de clases usadas
    ProyectoDAO proDao = new ProyectoDAO();
    AlertBuilder alert = new AlertBuilder();


    // componentes de la UI
    @FXML
    private TextField tfNombre;
    @FXML
    private Button btnActualizarPro;
    @FXML
    private Label labelError;
    @FXML
    private ComboBox<String> cbOrg;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextArea taActividades;
    @FXML
    private TextField tfCupo;
    @FXML
    private ComboBox<String> cbEstado;


    // inicializar la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // obtener proyecto de la BD y llenar el formulario con los datos del proyecto
        int id = Proyecto.proyectoSeleccionado.getId();
        Proyecto proyecto = proDao.obtenerProyecto(id);

        tfNombre.setText(proyecto.getNombre());
        taActividades.setText(proyecto.getActividades());
        taDescripcion.setText(proyecto.getDescripcion());
        tfCupo.setText(proyecto.getCupo());

        // inicializar ComboBox de organizaciones
        OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
        ArrayList<OrganizacionVinculada> organizaciones = orgDao.obtenerOrganizaciones();
        organizaciones.forEach(org -> {
            //cbOrg.getItems().add(org.getNombre()+" ("+org.getOrganizacionId()+")");
        });

        // inicializar ComboBox de estado del proyecto
        cbEstado.getItems().setAll("Disponible", "No disponible");
    }

    // métodos de la UI
    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) btnActualizarPro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actualizarPro(ActionEvent event) {
        if(formularioValido()){
            Proyecto proyecto = generarProyecto();
            if(proDao.actualizarProyecto(proyecto)){
                TablaProyectosController.getInstance().popularTabla();
                // si ocurré una excepción al actualizar el proyecto, no se actualiza la tabla
            }
            Stage stage = (Stage) btnActualizarPro.getScene().getWindow();
            stage.close();
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }


    // métodos
    public boolean formularioValido() {
        return !tfNombre.getText().equals("") && !taDescripcion.getText().equals("") &&
                !taActividades.getText().equals("") && !tfCupo.getText().equals("") &&
                !cbOrg.getSelectionModel().isEmpty() && !cbEstado.getSelectionModel().isEmpty();
    }

    public Proyecto generarProyecto(){
        Proyecto proyecto = Proyecto.proyectoSeleccionado;
        // expresión regular para obtener el id de la organización seleccionada
        String regex = "^.*?\\([^\\d]*(\\d+)[^\\d]*\\).*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cbOrg.getValue());
        if(m.find()){
            proyecto.setOrganizacion(m.group(1));
        }

        proyecto.setNombre(tfNombre.getText());
        proyecto.setCupo(tfCupo.getText());
        proyecto.setDescripcion(taDescripcion.getText());
        proyecto.setActividades(taActividades.getText());
        proyecto.setEstado(cbEstado.getValue());

        return proyecto;
    }
}
