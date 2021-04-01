package coord;

import DataAccess.DAO.OrganizacionVinculadaDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.OrgComboBox;
import Dominio.OrganizacionVinculada;
import Dominio.Proyecto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModalRegistrarProController implements Initializable {
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
    ProyectoDAO proDao = new ProyectoDAO();

    @FXML
    private TextField tfNombre;

    @FXML
    private Button btnRegistrarPro;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<OrganizacionVinculada> organizaciones = orgDao.obtenerOrganizaciones();
        organizaciones.forEach(org -> {
            cbOrg.getItems().add(org.getNombre()+" ("+org.getOrganizacionId()+")");
        });
    }

    public void cancelarRegistro(ActionEvent actionEvent) {
        Stage stage = (Stage) btnRegistrarPro.getScene().getWindow();
        stage.close();
    }

    public void registrarPro(ActionEvent actionEvent) {
        if(formularioValido()){
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(tfNombre.getText());
            proyecto.setOrganizacion(cbOrg.getValue());
            proyecto.setCupo(tfCupo.getText());
            proyecto.setEstado("Disponible");
            proyecto.setDescripcion(taDescripcion.getText());
            proyecto.setActividades(taActividades.getText());

            //TODO: regex para obtener organizacion id
            //TODO: update proyecto

            proDao.registrarProyecto(proyecto);
            TablaProyectosController.getInstance().popularTabla();
            Stage stage = (Stage) btnRegistrarPro.getScene().getWindow();
            stage.close();
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }

    public boolean formularioValido() {
        return !tfNombre.getText().equals("") && !cbOrg.getValue().equals("")
                && !taDescripcion.getText().equals("") && !taDescripcion.getText().equals("")
                && !tfCupo.getText().equals("");
    }
}
