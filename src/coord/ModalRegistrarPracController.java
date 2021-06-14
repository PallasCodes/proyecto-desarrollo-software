package coord;

import DataAccess.DAO.PracticanteDAO;
import DataAccess.DAO.UsuarioDAO;
import Dominio.Practicante;
import Dominio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Validaciones;

public class ModalRegistrarPracController {
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
            if(datosValidos()){
                if (validaciones.validacionMatricula(tfMatricula.getText())) {
                    Usuario usuario = generarUsuario();
                    if (usuarioDAO.registrarUsuario(usuario)) {
                        Practicante practicante = generarPracticante();
                        if (practicanteDao.registrarPracticante(practicante)) {
                            TablaPracticantesController.getInstance().popularTabla();
                        }
                        Stage stage = (Stage) registrarPrac.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    labelError.setText("Formato de matrícula erróneo.");
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
                && !tfContraseña.getText().equals("") && !tfTelefono.getText().equals("") && !tfFacultad.getText().equals("")
                && !tfCorreo.getText().equals("") && !tfMatricula.getText().equals("") && !tfPeriodo.getText().equals("");
    }

    public boolean datosValidos(){
        boolean telefonoValido = validaciones.validacionTelefono(tfTelefono.getText());
        return telefonoValido;
    }

    public Usuario generarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setPrimerApellido(tfPrimerApe.getText());
        usuario.setSegundoApellido(tfSegundoApe.getText());
        usuario.setContraseña(tfContraseña.getText());
        usuario.setRol("practicante");
        usuario.setTelefono(tfTelefono.getText());
        usuario.setCorreo(tfCorreo.getText());
        usuario.setFacultad(tfFacultad.getText());
        usuario.setMatricula(tfMatricula.getText());

        return usuario;
    }

    public Practicante generarPracticante() {
        Practicante practicante = new Practicante();
        practicante.setMatricula(tfMatricula.getText());
        practicante.setEstado("sin asignar");
        practicante.setPeriodo(tfPeriodo.getText());

        return practicante;
    }
}
