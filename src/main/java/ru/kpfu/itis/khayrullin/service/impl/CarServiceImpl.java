package ru.kpfu.itis.khayrullin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.khayrullin.model.Car;
import ru.kpfu.itis.khayrullin.repository.CarRepository;
import ru.kpfu.itis.khayrullin.service.CarService;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car findbyModel(String model) {
        return carRepository.findOneByModel(model);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }
}
