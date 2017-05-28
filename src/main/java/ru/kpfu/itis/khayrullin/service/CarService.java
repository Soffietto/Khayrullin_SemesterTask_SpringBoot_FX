package ru.kpfu.itis.khayrullin.service;

import ru.kpfu.itis.khayrullin.model.Car;

import java.util.List;

public interface CarService {

    void add(Car car);

    void delete(Car car);

    Car findbyModel(String model);

    List<Car> getAll();
}
