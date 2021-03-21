package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import data.UsuarioData;
import utils.SceneSwitcher;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    private TextField tfUsuario;

    @FXML
    private TextField tfContraseña;

    @FXML
    private Button btnEntrar;

    @FXML
    private Label labelErrores;

    @FXML
    void entrar(ActionEvent event) {
        if(camposValidos()){
            String usuario = UsuarioData.iniciarSesion(tfUsuario.getText(),tfContraseña.getText());
            try{
                switch(usuario){
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
                        System.out.println("error");
                        break;
                }
            }catch(IOException ex){
                ex.printStackTrace();
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
