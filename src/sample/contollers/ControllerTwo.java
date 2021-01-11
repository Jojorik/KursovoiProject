package sample.contollers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.database.DatabaseProduct;


/**
 * Класс, отвечающий за отображение эллементов окна операций приложения
 * @author Ponomarev G.I.
 */

public class ControllerTwo {


    public Text infoField;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button coldProductButton;

    @FXML
    private Button drinksProductButton;

    @FXML
    private Button hotProductButton;


    @FXML
    private TextField hotNameProduct;

    @FXML
    private TextField countWarehouseColdProduct;

    @FXML
    private TextField drinksNameProduct;

    @FXML
    private TextField countShowcaseColdProduct;
    @FXML
    private TextField coldNameProduct;

    @FXML
    private TextField countWarehouseHotProduct;

    @FXML
    private TextField countShowcaseHotProduct;

    @FXML
    private TextField countWarehouseDrinksProduct;

    @FXML
    private TextField countShowcaseDrinksProduct;


    @FXML
    void initialize() {
    addColdProduct();
    addHotProduct();
    addDrinksProduct();
    }

    /**
     * Метод, позволяет при вводе данных в поля ввода и нажатии на кнопку отправить данные в Базу данных холодных продуктов
     */
    private void addColdProduct(){
        DatabaseProduct dbhandler = new DatabaseProduct();
        coldProductButton.setOnAction(event ->{
            dbhandler.insertColdProductDatabase(coldNameProduct.getText(), countWarehouseColdProduct.getText(), countShowcaseColdProduct.getText());

        });
    }
    /**
     * Метод, позволяет при вводе данных в поля ввода и нажатии на кнопку отправить данные в Базу данных горячих продуктов
     */
    private void addHotProduct(){
        DatabaseProduct databaseProduct = new DatabaseProduct();
        hotProductButton.setOnAction(event->{
            databaseProduct.insertHotProductDatabase(hotNameProduct.getText(),countWarehouseHotProduct.getText(),countShowcaseHotProduct.getText());
        });
    }
    /**
     * Метод, позволяет при вводе данных в поля ввода и нажатии на кнопку отправить данные в Базу данных напитков
     */
    private void addDrinksProduct(){
    DatabaseProduct databaseProduct = new DatabaseProduct();
    drinksProductButton.setOnAction(event->{
        databaseProduct.insertDrinkProductDatabase(drinksNameProduct.getText(),countWarehouseDrinksProduct.getText(),countShowcaseDrinksProduct.getText());
    });
    }

}
