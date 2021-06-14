package utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Archivos {

    public void subirArchivo(String direccionArchivo) {
        String direccionDestino = "Files";
        File archivoFuente = new File(direccionArchivo);
        File destino = new File (direccionDestino);
        try {
            FileUtils.copyFileToDirectory(archivoFuente, destino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File obtenerArchivo(Stage stage) {
        FileChooser chooser = new FileChooser();
        File archivo = chooser.showOpenDialog(stage);
        return archivo;
    }


}
