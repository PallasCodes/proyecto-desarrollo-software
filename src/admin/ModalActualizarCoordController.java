package admin;

import DataAccess.DAO.CoordinadorDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Usuario;
import coord.TablaProyectosController;
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

public class ModalActualizarCoordController implements Initializable {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    CoordinadorDAO coordDao = new CoordinadorDAO();

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
    private TextField tfTelefono;
    @FXML
    private TextField tfFacultad;
    @FXML
    private TextField tfCorreo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int id = Usuario.coordinadorSeleccionado.getUsuarioId();
        Usuario coord = usuarioDao.obtenerUsuario(id);

        tfNombre.setText(coord.getNombre());
        tfPrimerApe.setText(coord.getPrimerApe());
        tfSegundoApe.setText(coord.getSegundoApe());
        tfTelefono.setText(coord.getTelefono());
        tfFacultad.setText(coord.getFacultad());
        tfCorreo.setText(coord.getCorreo());
    }

    public void cancelarRegistro(ActionEvent actionEvent) {
    }

    public void actualizarCoord(ActionEvent actionEvent) {
        if(camposCompletos()){
            Usuario coordinador = generarUsuario();
            if(coordDao.actualizarCoord(coordinador)){
                TablaCoordinadoresController.getInstance().popularTabla();
                // si ocurré una excepción al actualizar el proyecto, no se actualiza la tabla
            }
            Stage stage = (Stage) registrarPrac.getScene().getWindow();
            stage.close();
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }

    public boolean camposCompletos(){
        return !tfNombre.getText().equals("") && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("");
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApe(tfPrimerApe.getText());
        usuario.setSegundoApe(tfSegundoApe.getText());
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());
        usuario.setUsuarioId(Usuario.coordinadorSeleccionado.getUsuarioId());

        return usuario;
    }
}
