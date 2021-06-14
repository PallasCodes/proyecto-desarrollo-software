package coord;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Practicante;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Validaciones;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalActualizarPracticanteController implements Initializable {
    // instancias de clases usadas
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    PracticanteDAO practicanteDao = new PracticanteDAO();
    Validaciones validaciones = new Validaciones();


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
            if(validaciones.validacionTelefono(tfTelefono.getText())){
                Usuario usuario = generarUsuario();
                if(usuarioDAO.actualizarUsuario(usuario)) {
                    Practicante practicante = generarPracticante();
                    if(practicanteDao.actualizarPracticante(practicante)){
                        TablaPracticantesController.getInstance().popularTabla();
                    }
                    Stage stage = (Stage) registrarPrac.getScene().getWindow();
                    stage.close();
                }
            } else {
                labelError.setText("Formato del teléfono erróneo.");
            }
        } else {
            labelError.setText("*Llene todos los campos del formulario");
        }
    }


    // métodos
    public boolean camposCompletos(){
        return !tfNombre.getText().equals("") && !tfPrimerApe.getText().equals("") && !tfSegundoApe.getText().equals("")
                && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("") && !tfPeriodo.getText().equals("");
    }


    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApellido(tfPrimerApe.getText());
        usuario.setSegundoApellido(tfSegundoApe.getText());
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());
        usuario.setMatricula(Practicante.practicanteSeleccionado.getMatricula());

        return usuario;
    }

    public Practicante generarPracticante() {
        Practicante practicante = new Practicante();
        practicante.setMatricula(Practicante.practicanteSeleccionado.getMatricula());
        practicante.setPeriodo(tfPeriodo.getText());

        return practicante;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Practicante practicante = Practicante.practicanteSeleccionado;
        Usuario usuario = usuarioDAO.obtenerUsuarioPorMat(practicante.getMatricula());

        tfCorreo.setText(usuario.getCorreo());
        tfFacultad.setText(usuario.getFacultad());
        tfNombre.setText(practicante.getNombre());
        tfPeriodo.setText(practicante.getPeriodo());
        tfPrimerApe.setText(practicante.getPrimerApellido());
        tfSegundoApe.setText(practicante.getSegundoApellido());
        tfTelefono.setText(usuario.getTelefono());
    }
}