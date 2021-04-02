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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModalActualizarProController implements Initializable {
    ProyectoDAO proDao = new ProyectoDAO();

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

    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) btnActualizarPro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actualizarPro(ActionEvent event) {
        if(formularioValido()){
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

            proDao.actualizarProyecto(proyecto);
            TablaProyectosController.getInstance().popularTabla();
            Stage stage = (Stage) btnActualizarPro.getScene().getWindow();
            stage.close();
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // obtener proyecto de la BD y llenar el formulario con los datos del proyecto
        int id = Integer.parseInt(Proyecto.proyectoSeleccionado.getProyectoId());
        Proyecto proyecto = proDao.obtenerProyecto(id);

        tfNombre.setText(proyecto.getNombre());
        taActividades.setText(proyecto.getActividades());
        taDescripcion.setText(proyecto.getDescripcion());
        tfCupo.setText(proyecto.getCupo());

        // inicializar ComboBox de organizaciones
        OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
        ArrayList<OrganizacionVinculada> organizaciones = orgDao.obtenerOrganizaciones();
        organizaciones.forEach(org -> {
            cbOrg.getItems().add(org.getNombre()+" ("+org.getOrganizacionId()+")");
        });

        // inicializar ComboBox de estado del proyecto
        cbEstado.getItems().setAll("Disponible", "No disponible");
    }

    public boolean formularioValido() {
        return !tfNombre.getText().equals("") && !taDescripcion.getText().equals("") &&
                !taActividades.getText().equals("") && !tfCupo.getText().equals("") &&
                !cbOrg.getSelectionModel().isEmpty() && !cbEstado.getSelectionModel().isEmpty();
    }
}
