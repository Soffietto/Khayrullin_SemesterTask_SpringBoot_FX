package ru.kpfu.itis.khayrullin.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.khayrullin.controller.util.SceneChangingHandler;
import ru.kpfu.itis.khayrullin.model.Car;
import ru.kpfu.itis.khayrullin.service.CarService;
import ru.kpfu.itis.khayrullin.util.Validator;

import java.util.ConcurrentModificationException;

public class CarFormController {

    @Autowired
    private Validator validator;

    @FXML
    private TextField et_model;

    @FXML
    private TextField et_year;

    @FXML
    private TextField et_mileage;

    @FXML
    private TextField et_engine_power;

    @FXML
    private TextField et_cost;

    @Autowired
    private CarService carService;

    private ObservableList<Car> carsFromTable;

    public void setCarsFromTable(ObservableList<Car> carsFromTable) {
        this.carsFromTable = carsFromTable;
    }

    private SceneChangingHandler handler;

    public CarFormController() {
        handler = SceneChangingHandler.getInstance();
    }

    private boolean isUpdating = false;

    private Car selectedCar;

    @FXML
    public void submit(ActionEvent event) {
        String model = et_model.getText();
        String year = et_year.getText();
        String mileage = et_mileage.getText();
        String enginePower = et_engine_power.getText();
        String costPerHour = et_cost.getText();

        try {
            validator.isCarFormValid(model, year, mileage, enginePower, costPerHour);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return;
        }

        Car car = new Car();
        car.setModel(model);
        car.setYear(year);
        car.setMileage(mileage);
        car.setEnginePower(enginePower);
        car.setCostPerHour(costPerHour);

        if (selectedCar != null)
            car.setId(selectedCar.getId());

        carService.add(car);
        carsFromTable.add(car);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "car successfully added");

        try {
            if (isUpdating) {
                for (Car i : carsFromTable) {
                    if (car.getId() == i.getId()) {
                        carsFromTable.remove(i);
                        carService.delete(i);
                    }
                }

            }
        } catch (ConcurrentModificationException e) {
            alert = new Alert(Alert.AlertType.INFORMATION, "car successfully updated");
        }
        handler.addCarToTables(car);
        alert.show();

    }

    public void setSelectedCar(Car car) {
        selectedCar = car;
        isUpdating = true;
        et_model.setText(car.getModel());
        et_year.setText(car.getYear());
        et_mileage.setText(car.getMileage());
        et_engine_power.setText(car.getEnginePower());
        et_cost.setText(car.getCostPerHour());
    }

    public void clear() {
        selectedCar = null;
        Car car = new Car();
        car.setModel("");
        car.setMileage("");
        car.setYear("");
        car.setCostPerHour("");
        car.setEnginePower("");
        setSelectedCar(new Car());
        isUpdating = false;
    }
}
