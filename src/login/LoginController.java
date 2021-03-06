package login;

import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.SceneSwitcher;
import java.io.IOException;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    SceneSwitcher switcher = new SceneSwitcher();
    UsuarioDAO usuarioDao = new UsuarioDAO();

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfContraseña;
    @FXML
    private Button btnEntrar;
    @FXML
    private Label labelErrores;

    @FXML
    void entrar(ActionEvent event) {
        if (camposValidos()) {
            Usuario usuario = usuarioDao.obtenerUsuarioPorMat(tfUsuario.getText());
            if (usuario.getContraseña().equals(tfContraseña.getText())) {
                Usuario.usuarioActual = usuario;
            } else {
                labelErrores.setText("*Usuario y/o contraseña \n incorrectos");
            }
            try {
                switch (usuario.getRol()) {
                    case "admin":
                        switcher.switchScene(event, stage, scene, "../admin/AdminInicio.fxml");
                        break;
                    case "coord":
                        switcher.switchScene(event, stage, scene, "../coord/CoordInicio.fxml");
                        break;
                    case "profesor":
                        switcher.switchScene(event, stage, scene, "../profesor/ProfesorInicio.fxml");
                        break;
                    case "practicante":
                        switcher.switchScene(event, stage, scene, "../practicante/PracticanteInicio.fxml");
                        break;
                    default:
                        break;
                }
            } catch (IOException ex) {
            }
        }
    }

    public boolean camposValidos(){
        if(tfContraseña.getText().equals("") || tfUsuario.getText().equals("")){
            labelErrores.setText("*Introduzca su usuario y \ncontraseña");
            return false;
        }
        return true;
    }

}
