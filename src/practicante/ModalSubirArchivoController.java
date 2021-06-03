package practicante;

import DataAccess.DAO.ArchivoDAO;
import Dominio.Archivo;
import Dominio.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlertBuilder;
import utils.Archivos;
import utils.SceneSwitcher;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ModalSubirArchivoController {

    @FXML
    TextField archivoTextField;
    @FXML
    TextField tipoArchivoTextField;
    @FXML
    TextField nombreArchivo;

    ArchivoDAO archivoDAO = new ArchivoDAO();
    AlertBuilder alertBuilder = new AlertBuilder();
    Archivos archivos = new Archivos();
    SceneSwitcher sw = new SceneSwitcher();

    File archivoSubido;
    Stage stage;
    Scene scene;

    public void obtenerArchivo() {
        archivoSubido = archivos.obtenerArchivo(stage);
        archivoTextField.setText(archivoSubido.getName());
    }

    @FXML
    public void irInicio(MouseEvent event) throws IOException {
        sw.switchSceneMouse(event, stage, scene, "../admin/AdminInicio.fxml");
    }

    public boolean comprobarCamposVacios() {
        if (!nombreArchivo.getText().isEmpty() && !tipoArchivoTextField.getText().isEmpty()) {
            if (!archivoTextField.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void guardarArchivo() {
        Archivo archivo = new Archivo();
        archivo.setNombreArchivo(nombreArchivo.getText());
        archivo.setTipoArchivo(tipoArchivoTextField.getText());
        archivo.setMatricula(Usuario.usuarioActual.getMatricula());
        archivo.setDireccionArchivo("Files/" + archivoSubido.getName());
        int guardadoExitoso = 0;
        if (archivoSubido != null) {
            if (comprobarCamposVacios()) {
                try {
                    guardadoExitoso = archivoDAO.guardarArchivo(archivo);
                    archivos.subirArchivo(archivoSubido.getAbsolutePath());
                } catch (SQLException sqlException) {
                    alertBuilder.exceptionAlert("No es posible acceder a la base de datos. Intente más tarde.");
                    sqlException.printStackTrace();
                }
                if (guardadoExitoso == 1) {
                    alertBuilder.successAlert("¡Registro exitoso!");
                    stage = (Stage) archivoTextField.getScene().getWindow();
                    stage.close();
                    SubirArchivoController.getInstance().popularTabla();
                }
            } else {
                alertBuilder.errorAlert("Llene todos los campos.");
            }
        } else {
            alertBuilder.errorAlert("Seleccione un archivo.");
        }
    }
}
