package ru.kpfu.itis.khayrullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.khayrullin.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findOneByModel(String model);
}
