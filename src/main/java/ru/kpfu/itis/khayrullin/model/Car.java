package ru.kpfu.itis.khayrullin.model;

import javax.persistence.*;

@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String year;
    private String mileage;

    @Column(name = "engine_power")
    private String enginePower;

    @Column(name = "cost_per_hour")
    private String costPerHour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(String costPerHour) {
        this.costPerHour = costPerHour;
    }
}
