package admin;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Practicante;
import Dominio.Usuario;
import coord.TablaPracticantesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalRegistrarCoordController {
    // instancias de clases usadas
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    PracticanteDAO practicanteDao = new PracticanteDAO();


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

    // métodos de la UI
    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) registrarPrac.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registrarPrac(ActionEvent event) {
        if(camposCompletos()){
            Usuario usuario = generarUsuario();
            if(usuarioDAO.registrarUsuario(usuario)) {
                TablaCoordinadoresController.getInstance().popularTabla();
                Stage stage = (Stage) registrarPrac.getScene().getWindow();
                stage.close();
            }
        } else {
            labelError.setText("*Llene todos los campos del formulario");
        }
    }


    // métodos
    public boolean camposCompletos(){
        return !tfNombre.getText().equals("") && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfContraseña.getText().equals("") && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("");
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApe(tfPrimerApe.getText());
        usuario.setSegundoApe(tfSegundoApe.getText());
        usuario.setContraseña(tfContraseña.getText());
        usuario.setRol("coord");
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());

        return usuario;
    }

    public Practicante generarPracticante() {
        Practicante practicante = new Practicante();
        practicante.setEstado("Sin asignar");
        practicante.setUsuarioId(usuarioDAO.obtenerIdUsuario(tfCorreo.getText()));

        return practicante;
    }
}
