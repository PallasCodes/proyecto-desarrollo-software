package coord;

import DataAccess.DAO.OrganizacionVinculadaDAO;
import Dominio.OrganizacionVinculada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalActualizarOrgController implements Initializable {
    // instancias de clases usadas
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
    OrganizacionVinculada organizacion = OrganizacionVinculada.orgSeleccionada;


    // componentes de la UI
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Button registrarOrg;
    @FXML
    private Label labelError;


    // métodos de la UI
    @FXML
    public void cancelarRegistro(ActionEvent actionEvent) {
        Stage stage = (Stage) registrarOrg.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registrarOrg(ActionEvent actionEvent) {
        OrganizacionVinculada org = new OrganizacionVinculada();
        org.setNombre(tfNombre.getText());
        org.setCorreo(tfCorreo.getText());
        org.setDireccion(tfDireccion.getText());

        if(formularioValido()){
            orgDao.actualizarOrganizacion(org, OrganizacionVinculada.orgSeleccionada.getNombre());
            TablaOrganizacionesController.getInstance().popularTabla();
            Stage stage = (Stage) registrarOrg.getScene().getWindow();
            stage.close();
        } else{
            labelError.setText("*Llene todos los campos del formulario");
        }
    }


    // métodos
    public boolean formularioValido(){
        return !tfNombre.getText().equals("") && !tfCorreo.getText().equals("") && !tfDireccion.getText().equals("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCorreo.setText(organizacion.getCorreo());
        tfDireccion.setText(organizacion.getDireccion());
        tfNombre.setText(organizacion.getNombre());
    }
}
