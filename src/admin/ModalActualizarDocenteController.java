
package admin;

import DataAccess.DAO.DocenteDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.Validaciones;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalActualizarDocenteController implements Initializable {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    DocenteDAO docenteDAO = new DocenteDAO();

    // componentes de UI
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPrimerApe;
    @FXML
    private TextField tfSegundoApe;
    @FXML
    private Button registrarDocente;
    @FXML
    private Label labelError;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfFacultad;
    @FXML
    private TextField tfCorreo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String numeroPersonal = Usuario.docenteSeleccionado.getMatricula();
        Usuario docente = usuarioDao.obtenerUsuarioPorMat(numeroPersonal);

        tfNombre.setText(docente.getNombre());
        tfPrimerApe.setText(docente.getPrimerApellido());
        tfSegundoApe.setText(docente.getSegundoApellido());
        tfTelefono.setText(docente.getTelefono());
        tfFacultad.setText(docente.getFacultad());
        tfCorreo.setText(docente.getCorreo());
    }

    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) registrarDocente.getScene().getWindow();
        stage.close();
    }

    public void actualizarDocente(ActionEvent actionEvent) {
        if(validarCampos()) {
            Usuario docente = generarUsuario();
            if (docenteDAO.actualizarDocente(docente)) {
                TablaDocenteController.getInstance().popularTabla();
                // si ocurré una excepción al actualizar el proyecto, no se actualiza la tabla
            }
            Stage stage = (Stage) registrarDocente.getScene().getWindow();
            stage.close();
        }
    }

    public boolean validarCampos(){
        Validaciones validaciones = new Validaciones();
        if (!tfNombre.getText().equals("") && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("")) {
            if (validaciones.validacionTelefono(tfTelefono.getText())) {
                return true;
            } else {
                AlertBuilder alertBuilder = new AlertBuilder();
                alertBuilder.errorAlert("Verificar formato en campo Teléfono.");
                return false;
            }
        } else {
            AlertBuilder alertBuilder = new AlertBuilder();
            alertBuilder.errorAlert("Verificar campos vacíos.");
        }
        return false;
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApellido(tfPrimerApe.getText());
        usuario.setSegundoApellido(tfSegundoApe.getText());
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());
        usuario.setMatricula(Usuario.docenteSeleccionado.getMatricula());

        return usuario;
    }
}