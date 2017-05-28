package ru.kpfu.itis.khayrullin.controller.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.khayrullin.entity.View;
import ru.kpfu.itis.khayrullin.model.Car;
import ru.kpfu.itis.khayrullin.model.Rent;
import ru.kpfu.itis.khayrullin.service.CarService;
import ru.kpfu.itis.khayrullin.service.RentService;

import java.util.List;

@Component
public class SceneChangingHandler {

    private static SceneChangingHandler sceneChangingHandlerSingleton;
    private ObservableList<Car> carTable;
    private ObservableList<Rent> rentTable;

    public static SceneChangingHandler getInstance(){
        if (sceneChangingHandlerSingleton == null){
            sceneChangingHandlerSingleton = new SceneChangingHandler();
        }
        return sceneChangingHandlerSingleton;
    }

    public void nextScene(ActionEvent event, View view){

        Parent parent = view.getView();
        Scene scene = parent.getScene();

        if (scene == null){
            scene = new Scene(parent);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    public Stage newStage(View view){
        Parent parent = view.getView();
        Scene scene  = parent.getScene();

        if (scene == null){
            scene = new Scene(parent);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    public void addCarToTables(Car car){
        carTable.add(car);
    }

    public void addRentToTables(Rent rent){
        rentTable.add(rent);
    }

    public ObservableList initCarTable(TableView tableView, CarService carService){
        List<Car> cars = carService.getAll();
        carTable = FXCollections.observableArrayList(cars);

        TableColumn<Car, String> model = new TableColumn<>("Model");
        model.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Car, String> year = new TableColumn<>("Year");
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Car, String> mileage = new TableColumn<>("Mileage");
        mileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn<Car, String> enginePower = new TableColumn<>("Engine Rower (h. p.)");
        enginePower.setCellValueFactory(new PropertyValueFactory<>("enginePower"));

        TableColumn<Car, String> costPerHour = new TableColumn<>("Cost per hour");
        costPerHour.setCellValueFactory(new PropertyValueFactory<>("costPerHour"));

        tableView.getColumns().setAll(model, year, mileage, enginePower, costPerHour);
        tableView.setItems(carTable);

        return carTable;
    }

    public ObservableList initRentTable(TableView tableView,RentService rentService){
        List<Rent> rents = rentService.getAll();
        rentTable = FXCollections.observableArrayList(rents);

        TableColumn<Car, String> fullName = new TableColumn<>("Full name");
        fullName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Car, String> phoneNumber = new TableColumn<>("Phone Number");
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Car, String> model = new TableColumn<>("Mark/Model");
        model.setCellValueFactory(new PropertyValueFactory<>("car"));

        TableColumn<Car, String> deliveryDate = new TableColumn<>("Delivery Date");
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("beginningDate"));

        TableColumn<Car, String> returnDate = new TableColumn<>("Return date");
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        tableView.getColumns().setAll(fullName,phoneNumber,model,deliveryDate,returnDate);
        tableView.setItems(rentTable);

        return rentTable;
    }

}
