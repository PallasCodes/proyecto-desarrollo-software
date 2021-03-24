package coord;

import Dominio.OrganizacionVinculada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import data.DAO.OrganizacionVinculadaDAO;
import javafx.stage.Stage;

public class ModalRegistrarOrgController {
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();

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
        orgDao.registrarOrganizacion(org);

        Stage stage = (Stage) registrarOrg.getScene().getWindow();
        stage.close();
    }
}
