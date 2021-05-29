package admin;

import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.Validaciones;

public class ModalRegistrarDocenteController {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

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
    private PasswordField tfContraseña;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfFacultad;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private TextField tfConfirmarContraseña;
    @FXML
    private Label contraseñasLabel;


    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) registrarDocente.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registrarDocente(ActionEvent event) {
        if(validarCampos()) {
            Usuario usuario = generarUsuario();
            if (usuarioDAO.registrarUsuario(usuario)) {
                TablaDocenteController.getInstance().popularTabla();
                Stage stage = (Stage) registrarDocente.getScene().getWindow();
                stage.close();
            }
        }
    }

    public boolean validarCampos(){
        Validaciones validaciones = new Validaciones();
        if (!tfNumeroPersonal.getText().equals("") && !tfNombre.getText().equals("")
                && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfContraseña.getText().equals("") && !tfTelefono.getText().equals("")
                && !tfFacultad.getText().equals("") && !tfCorreo.getText().equals("")
                && !tfConfirmarContraseña.getText().equals("")) {
            String numeroPersonalText = tfNumeroPersonal.getText();
            String telefonoText = tfTelefono.getText();
            if (validaciones.validacionNumeroPer(numeroPersonalText)) {
                if (validaciones.validacionTelefono(telefonoText)) {
                    String contraseña = tfContraseña.getText();
                    String confirmacion = tfConfirmarContraseña.getText();
                    if (validaciones.confirmarContraseña(contraseña, confirmacion)) {
                        contraseñasLabel.setVisible(false);
                        return true;
                    } else {
                        contraseñasLabel.setVisible(true);
                    }
                } else {
                    AlertBuilder alertBuilder = new AlertBuilder();
                    alertBuilder.errorAlert("Verificar formato en campo Teléfono.");
                }
            } else {
                AlertBuilder alertBuilder = new AlertBuilder();
                alertBuilder.errorAlert("Verificar formato de número de personal.");
            }
        } else {
            AlertBuilder alertBuilder = new AlertBuilder();
            alertBuilder.errorAlert("Verificar campos vacíos.");
        }
        return false;
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setMatricula(tfNumeroPersonal.getText());
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApellido(tfPrimerApe.getText());
        usuario.setSegundoApellido(tfSegundoApe.getText());
        usuario.setContraseña(tfContraseña.getText());
        usuario.setRol("profesor");
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());

        return usuario;
    }

}
