package login;

import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import utils.AlertBuilder;

public class ModalCambiarContraseñaController {
    @FXML
    private PasswordField contraNueva;
    @FXML
    private PasswordField contraNuevaRepetir;
    @FXML
    private PasswordField contraActual;
    @FXML
    private Label labelError;
    @FXML
    private Button btnCambiar;

    UsuarioDAO usuarioDao = new UsuarioDAO();
    AlertBuilder alert = new AlertBuilder();
    
    public void cancelarRegistro(ActionEvent actionEvent) {
    }

    public void cambiarContraseña(ActionEvent actionEvent) {
        if(camposLlenos()){
            if(formularioValido()){
                String contraseña = usuarioDao.obtenerContraseña(Usuario.usuarioActual);
                if(contraseña == ""){
                    labelError.setText("La contraseña actual no coincide");
                } else {
                    if(usuarioDao.cambiarContraseña(Usuario.usuarioActual,contraNueva.getText())){
                        alert.successAlert("Contraseña cambiada con éxito");
                        Stage stage = (Stage) btnCambiar.getScene().getWindow();
                        stage.close();
                    }
                }
            } else {
                labelError.setText("Las contraseñas nuevas no coinciden");
            }
        } else{
            labelError.setText("Llene todo el formulario");
        }
    }

    public boolean formularioValido(){
        return contraNueva.getText().equals(contraNuevaRepetir.getText());
    }

    public boolean camposLlenos(){
        return !contraNueva.getText().equals("") && !contraActual.getText().equals("")
                && !contraNuevaRepetir.getText().equals("");
    }
}
