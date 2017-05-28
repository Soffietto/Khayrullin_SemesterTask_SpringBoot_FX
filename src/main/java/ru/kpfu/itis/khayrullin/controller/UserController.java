package ru.kpfu.itis.khayrullin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.khayrullin.controller.util.SceneChangingHandler;
import ru.kpfu.itis.khayrullin.entity.View;
import ru.kpfu.itis.khayrullin.model.Car;
import ru.kpfu.itis.khayrullin.service.CarService;

import javax.annotation.PostConstruct;

public class UserController {

    private SceneChangingHandler handler;

    public UserController() {
        handler = SceneChangingHandler.getInstance();
    }

    @Autowired
    private View rentFormView;

    @Autowired
    private View signInView;

    @FXML
    private TableView<Car> tableView;

    @Autowired
    private CarService carService;

    @FXML
    public void initialize() {
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        handler.initCarTable(tableView, carService);
    }

    @FXML
    public void rentCar(ActionEvent event) {
        String model = null;
        try {
            model = tableView.getSelectionModel().getSelectedItem().getModel();
        } catch (NullPointerException e) {
            model = "";
        }
        RentFormController controller = (RentFormController) rentFormView.getController();
        controller.setModel(model);
        Stage stage = handler.newStage(rentFormView);
        stage.showAndWait();
    }

    @FXML
    public void logOut(ActionEvent event) {
        handler.nextScene(event, signInView);
    }
}
