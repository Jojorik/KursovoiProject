package sample.contollers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.database.DatabaseService;
import sample.objects.Service;
import java.io.File;

/**
 * Класс для работы, с формами услуг
 */

public class FormController {


    public ImageView image;
    public Label name;
    public Label cost;
    public Label sale;
    public Button deleteButton;

    public void initialization(Service service) {
        setItems(service);
        deleteService(service);
    }

    /**
     * Метод для загрузки объектов из бд
     * @param service
     */

    private void setItems(Service service){

        Image imageValue = getImage("D:/JavaWork/JavaFX/KursovoiProject/src/sample/resours/" + service.getImagePath());
        image.setImage(imageValue);
        name.setText(service.getTitle());
        cost.setText(String.valueOf(service.getCost()));
        sale.setText(String.valueOf(service.getSale()));

    }

    private Image getImage(String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }

    /**
     * Метод для редактирования услуги
     * @param actionEvent
     */
    public void edit(ActionEvent actionEvent) {

    }

    public void delete(ActionEvent actionEvent) {

    }

    /**
     * Метод для удаление услуги
     * @param service
     */
    private void deleteService(Service service){
        deleteButton.setOnAction(Event ->{
            DatabaseService.delete(service);
        });
    }

}
