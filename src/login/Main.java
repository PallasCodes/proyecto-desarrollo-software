package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Renshu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

// TODO: crud practicantes
// TODO: crud organizaciones
// TODO: arreglar cambiar contraseña
// TODO: arreglar login
// TODO: aumentar horas del alumno cuando se valida el reporte
// TODO: seccion proyecto
// TODO: seccion ver resultados autoevaluacion
// TODO: seccion alumnos
// TODO: detalles esteticos
