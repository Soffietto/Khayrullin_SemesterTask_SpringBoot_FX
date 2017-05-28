package ru.kpfu.itis.khayrullin.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.khayrullin.controller.util.SceneChangingHandler;
import ru.kpfu.itis.khayrullin.entity.View;
import ru.kpfu.itis.khayrullin.model.Car;
import ru.kpfu.itis.khayrullin.model.Rent;
import ru.kpfu.itis.khayrullin.service.CarService;
import ru.kpfu.itis.khayrullin.service.RentService;

import javax.annotation.PostConstruct;

public class AdminController {

    private ObservableList<Car> carData;
    private ObservableList<Rent> rentData;

    @Autowired
    private View signInView;

    @Autowired
    private CarService carService;

    @Autowired
    private RentService rentService;

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableView<Rent> rentTableView;

    @Autowired
    private View carFormView;

    @Autowired
    private View rentFormView;

    private SceneChangingHandler handler;

    public AdminController() {
        handler = SceneChangingHandler.getInstance();
    }

    @FXML
    public void addCar(ActionEvent event) {
        CarFormController controller = (CarFormController) carFormView.getController();
        controller.setCarsFromTable(carData);
        Stage stage = handler.newStage(carFormView);
        stage.showAndWait();
    }

    @FXML
    public void deleteCar(ActionEvent event) {
        Car car = carTableView.getSelectionModel().getSelectedItem();
        carService.delete(car);
        carTableView.getItems().remove(car);
    }

    @FXML
    public void editCar(ActionEvent event) {
        Car car = carTableView.getSelectionModel().getSelectedItem();
        CarFormController controller = (CarFormController) carFormView.getController();
        controller.setCarsFromTable(carData);
        controller.setSelectedCar(car);
        Stage stage = handler.newStage(carFormView);
        stage.showAndWait();
    }

    @FXML
    public void addRent(ActionEvent event) {
        RentFormController controller = (RentFormController) rentFormView.getController();
        controller.setRentsFromTable(rentData);
        Stage stage = handler.newStage(rentFormView);
        stage.showAndWait();
    }

    @FXML
    public void deleteRent(ActionEvent event) {
        Rent rent = rentTableView.getSelectionModel().getSelectedItem();
        rentService.delete(rent);
        rentTableView.getItems().remove(rent);
    }

    @FXML
    public void editRent(ActionEvent event) {
        Rent rent = rentTableView.getSelectionModel().getSelectedItem();
        RentFormController controller = (RentFormController) rentFormView.getController();
        controller.setRentsFromTable(rentData);
        controller.setSelectedRent(rent);
        Stage stage = handler.newStage(rentFormView);
        stage.showAndWait();
    }

    @FXML
    public void logOut(ActionEvent event) {
        handler.nextScene(event, signInView);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        carData = handler.initCarTable(carTableView, carService);
        rentData = handler.initRentTable(rentTableView, rentService);
    }
}
