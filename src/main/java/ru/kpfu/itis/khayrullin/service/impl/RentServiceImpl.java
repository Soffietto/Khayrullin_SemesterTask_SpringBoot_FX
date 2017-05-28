package ru.kpfu.itis.khayrullin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.khayrullin.model.Rent;
import ru.kpfu.itis.khayrullin.repository.RentRepository;
import ru.kpfu.itis.khayrullin.service.RentService;

import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public void add(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public void delete(Rent rent) {
        rentRepository.delete(rent);
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }
}
