package ru.kpfu.itis.khayrullin.service;

import ru.kpfu.itis.khayrullin.model.Rent;

import java.util.List;

public interface RentService {

    void add(Rent rent);

    void delete(Rent rent);

    List<Rent> getAll();
}
