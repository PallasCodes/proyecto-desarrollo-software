package coord;

import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModalRegistrarPracController {
    // instancias de clases usadas
    UsuarioDAO usuarioDAO = new UsuarioDAO();


    // componentes de UI
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPrimerApe;
    @FXML
    private TextField tfSegundoApe;
    @FXML
    private Button registrarPrac;
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
    private TextField tfMatricula;
    @FXML
    private TextField tfPeriodo;

    // métodos de la UI
    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) registrarPrac.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registrarPrac(ActionEvent event) {
        if(camposCompletos()){
            if(datosValidos()){
                Usuario usuario = generarUsuario();
                if(usuarioDAO.registrarUsuario(usuario)) {
                    Stage stage = (Stage) registrarPrac.getScene().getWindow();
                    stage.close();
                }
            } else {
                labelError.setText("*Ingrese datos validos");
            }
        } else {
            labelError.setText("*Llene todos los campos del formulario");
        }
    }

    // métodos
    public boolean camposCompletos(){
        return !tfNombre.getText().equals("") && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfContraseña.getText().equals("") && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("") && !tfMatricula.getText().equals("") && !tfPeriodo.getText().equals("");
    }

    public boolean datosValidos(){
        return true;
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApe(tfPrimerApe.getText());
        usuario.setSegundoApe(tfSegundoApe.getText());
        usuario.setContraseña(tfContraseña.getText());
        usuario.setRol("practicante");
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());

        return usuario;
    }
}
