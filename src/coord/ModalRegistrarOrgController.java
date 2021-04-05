package coord;

import Dominio.OrganizacionVinculada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DataAccess.DAO.OrganizacionVinculadaDAO;
import javafx.stage.Stage;

public class ModalRegistrarOrgController {
    // instancias de clases usadas
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();


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
            orgDao.registrarOrganizacion(org);
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
}
