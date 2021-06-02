package coord;

import DataAccess.DAO.OrganizacionVinculadaDAO;
import DataAccess.DAO.ProyectoDAO;
import Dominio.OrganizacionVinculada;
import Dominio.Proyecto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ModalRegistrarProController implements Initializable {
    // instancias usadas
    OrganizacionVinculadaDAO orgDao = new OrganizacionVinculadaDAO();
    ProyectoDAO proDao = new ProyectoDAO();


    // componentes de la UI
    @FXML
    private TextField tfNombre;
    @FXML
    private Button btnRegistrarPro;
    @FXML
    private Label labelError;
    @FXML
    private ComboBox<String> cbOrg;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextArea taActividades;
    @FXML
    private TextField tfCupo;


    // inicialización de la vista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<OrganizacionVinculada> organizaciones = orgDao.obtenerOrganizaciones();
        organizaciones.forEach(org -> {
           cbOrg.getItems().add(org.getNombre());
        });
    }

    // métodos de la UI
    @FXML
    public void registrarPro(ActionEvent actionEvent) {
        if(camposCompletos()){
            if(datosValidos()){
                Proyecto proyecto = generarProyecto();
                if(proDao.registrarProyecto(proyecto)) {
                    TablaProyectosController.getInstance().popularTabla();
                    // si ocurre una excepción al registrar el proyecto, no se actualiza la tabla
                }
                Stage stage = (Stage) btnRegistrarPro.getScene().getWindow();
                stage.close();
            } else {
                labelError.setText("Llene los campos con datos validos.");
            }
        } else {
            labelError.setText("Llena todos los campos del formulario");
        }
    }

    @FXML
    public void cancelarRegistro(ActionEvent actionEvent) {
        Stage stage = (Stage) btnRegistrarPro.getScene().getWindow();
        stage.close();
    }


    // métodos
    // genera el objeto Proyecto con los datos del formulario
    public Proyecto generarProyecto() {
        Proyecto proyecto = new Proyecto();

        /*
        // expresión regular para obtener el id de la organización seleccionada
        String regex = "^.*?\\([^\\d]*(\\d+)[^\\d]*\\).*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cbOrg.getValue());
        if(m.find()){
            proyecto.setOrganizacion(m.group(1));
        }

         */
        proyecto.setOrganizacion(cbOrg.getValue());
        proyecto.setNombre(tfNombre.getText());
        proyecto.setCupo(tfCupo.getText());
        proyecto.setDescripcion(taDescripcion.getText());
        proyecto.setActividades(taActividades.getText());

        return proyecto;
    }

    public boolean camposCompletos() {
        return !tfNombre.getText().equals("") && !cbOrg.getSelectionModel().isEmpty()
                && !taDescripcion.getText().equals("") && !taActividades.getText().equals("")
                && !tfCupo.getText().equals("");
    }

    public boolean datosValidos(){
        try{
            Integer.parseInt(tfCupo.getText());
        } catch(Exception ex){
            return false;
        }
        return true;
    }
}
