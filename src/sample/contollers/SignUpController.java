package sample.contollers;
/**
 * Класс, для работы с окном регистрации
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import sample.database.DataBaseHandler;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpInButton;

    @FXML
    private TextField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField signUpLastname;

    @FXML
    private TextField signUpName;

    @FXML
    private CheckBox signUpCheckMale;

    @FXML
    private CheckBox signUpCheckFemale;
    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField password;

    @FXML
    void initialize() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        signUpInButton.setOnAction(event ->{

            dbHandler.signUpUsers(signUpName.getText(),signUpLastname.getText(),login_field.getText(),password.getText(),signUpCountry.getText(),"Male");
           signUpInButton.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/scene/app.fxml"));
            try {
                load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = load.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } );
    }

}
