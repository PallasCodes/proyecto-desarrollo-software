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
import java.util.regex.Matcher;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

            //TODO: quitar javafxapi y config min height y weight

            proDao.registrarProyecto(proyecto);
            TablaProyectosController.getInstance().popularTabla();
            Stage stage = (Stage) btnRegistrarPro.getScene().getWindow();
            stage.close();
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }

    public boolean formularioValido() {
        return !tfNombre.getText().equals("") && !cbOrg.getSelectionModel().isEmpty()
                && !taDescripcion.getText().equals("") && !taActividades.getText().equals("")
                && !tfCupo.getText().equals("");
    }
}
