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
import sample.objects.User;
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

        signUpInButton.setOnAction(event ->{
            signUpNewUser();
            signUpInButton.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/scene/ServiceMenu.fxml"));
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

    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastname.getText();
        String userName = login_field.getText();
        String pass = password.getText();
        String location = signUpCountry.getText();
        String gend = "";
        if(signUpCheckMale.isSelected())
            gend = "Мужской";
        else
            gend = "Женский";
        User user = new User(firstName,lastName,userName,pass,location,gend);
        dbHandler.signUpUsers(user);

    }
}
