package sample.contollers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;
import sample.database.DataBaseHandler;

/**
 * Класс, отвечающий за отображение эллементов стартового окна и взаимодействие с эллементами управления
 * @author Ponomarev G.I.
 */

public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private Button authSignUpInButton;

    @FXML
    private PasswordField password_field;
    @FXML
    private Button signUpInButton;

    @FXML
    void initialize() {


        transitionEntranceUser();
        signUpUser();

    }

    /**
     * Метод обеспечивает вход пользователя(загрузку экрана с операциями приложения)
     */
    private void transitionEntranceUser(){
        authSignUpInButton.setOnAction(event ->{
            signUpNewUser();

            authSignUpInButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/scene/app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            signUpNewUser();




        });
    }




    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();


            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login and password is empty");


    }

    private void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter>=1){
            System.out.println("Success");
        }
    }

    /**
     * Метод загружает окно регистрации
     */
    private void signUpUser(){
        signUpInButton.setOnAction(event->{
            signUpInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
