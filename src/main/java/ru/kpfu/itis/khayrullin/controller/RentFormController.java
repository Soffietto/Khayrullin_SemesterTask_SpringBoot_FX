package ru.kpfu.itis.khayrullin.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.khayrullin.controller.util.SceneChangingHandler;
import ru.kpfu.itis.khayrullin.model.Rent;
import ru.kpfu.itis.khayrullin.service.RentService;
import ru.kpfu.itis.khayrullin.util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ConcurrentModificationException;

public class RentFormController {

    @Autowired
    private Validator validator;

    @Autowired
    private RentService rentService;
    @FXML
    private TextField et_name;
    @FXML
    private TextField et_phone_number;
    @FXML
    private TextField et_car;
    @FXML
    private DatePicker et_starting_date;
    @FXML
    private DatePicker et_return_date;

    private boolean isUpdating = false;

    private Rent selectedRent;

    private SceneChangingHandler handler;

    public RentFormController() {
        handler = SceneChangingHandler.getInstance();
    }

    private ObservableList<Rent> rentsFromTable;

    public void setModel(String model) {
        if (model != null) {
            et_car.setText(model);
        }
    }

    public void setRentsFromTable(ObservableList<Rent> rentsFromTable) {
        this.rentsFromTable = rentsFromTable;
    }

    @FXML
    public void submit(ActionEvent event) {
        String name = et_name.getText();
        String phoneNumber = et_phone_number.getText();
        String car = et_car.getText();
        String deliveryDate = String.valueOf(et_starting_date.getValue());
        String returnDate = String.valueOf(et_return_date.getValue());


        try {
            validator.isRentFormValid(name, phoneNumber, car, deliveryDate, returnDate);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.show();
            e.printStackTrace();
            return;
        }

        Rent rent = new Rent();
        rent.setName(name);
        rent.setPhoneNumber(phoneNumber);
        rent.setCar(car);
        rent.setBeginningDate(deliveryDate);
        rent.setReturnDate(returnDate);
        if (selectedRent != null)
            rent.setId(selectedRent.getId());

            rentService.add(rent);
            rentsFromTable.add(rent);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "rent successfully added");
        try {
            if (isUpdating) {
                for (Rent i : rentsFromTable) {
                    if (rent.getId() == i.getId()) {
                        rentsFromTable.remove(i);
                        rentService.delete(i);
                    }
                }
            }
        }  catch (ConcurrentModificationException e){
            alert = new Alert(Alert.AlertType.INFORMATION, "rent successfully updated");
        }
        handler.addRentToTables(rent);
        alert.show();

    }

    public void setSelectedRent(Rent rent){
        isUpdating = true;
        selectedRent = rent;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        et_car.setText(rent.getCar());
        et_name.setText(rent.getName());
        et_phone_number.setText(rent.getPhoneNumber());
        try {
            et_starting_date.setValue(LocalDate.parse(rent.getBeginningDate().toString(), dateTimeFormatter));
            et_return_date.setValue(LocalDate.parse(rent.getReturnDate().toString(), dateTimeFormatter));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clear(){
        selectedRent = null;
        setSelectedRent(new Rent());
        isUpdating = false;
    }
}
