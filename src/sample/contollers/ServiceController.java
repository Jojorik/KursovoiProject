package sample.contollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.database.DatabaseService;
import sample.objects.Service;



/**
 * Класс, для работы с услугами
 */

public class ServiceController {

    @FXML
    private Button sortByCost;

    @FXML
    private Button sortBySale;

    @FXML
    private TextField sortByName;

    @FXML
    private Button refresh;

    public Text infoField;
    public VBox formVBox;



    @FXML
    void initialize() {
        updateServices();
        setFormPanes(services);
        setRefreshOnAction();
        setSortByCostOnAction();

    }

    private ArrayList<Service> services;

    private void updateServices(){
        this.services = DatabaseService.getServices();
    }

    private void setFormPanes(ArrayList<Service> services){
        for (Service service:services) {
            Pane form = createFormPane(service);
            formVBox.getChildren().add(form);

        }
    }

    /**
     * Установка действия для кнопки сортировки по стоимости
     */
    private void setSortByCostOnAction() {
        sortByCost.setOnAction(event -> {
            ArrayList<Service> oldArray = new ArrayList<>(services);
            services.sort(comparatorCost());
            if (oldArray.equals(services))
                services.sort(comparatorCost().reversed());
            setFormsPanes(services);
        });
    }

    private Comparator<Service> comparatorCost() {
        return (object1, object2) -> Float.compare(object1.getCost(), object2.getCost());
    }


    /**
     * Метод, для загрузки форм на окне
     *
     * @return pane - Pane формы сервиса
     */

    private Pane createFormPane(Service service) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/scene/ServiceForm.fxml"));
            Pane pane = loader.load();

            FormController formController = loader.getController();
            formController.initialization(service);


            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Установка дейтсвия для кнопки обновления списка продуктов
     */
    private void setRefreshOnAction() {
        refresh.setOnAction(event -> {
            updateServices();
            setFormsPanes(services);
        });
    }
    private void setFormsPanes(ArrayList<Service> services) {
        formVBox.getChildren().clear();
        for (Service service : services) {
            Pane form = createFormPane(service);
            formVBox.getChildren().add(form);
        }
    }
}
