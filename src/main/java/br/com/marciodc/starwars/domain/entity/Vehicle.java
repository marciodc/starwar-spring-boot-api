package br.com.marciodc.starwars.domain.entity;

import java.util.Objects;

public class Vehicle {
    private String vehicleUrl;

    public Vehicle() {
    }

    public Vehicle(String vehicleUrl) {
        this.vehicleUrl = vehicleUrl;
    }

    public String getVehicleUrl() {
        return vehicleUrl;
    }

    public void setVehicleUrl(String vehicleUrl) {
        this.vehicleUrl = vehicleUrl;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleUrl='" + vehicleUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleUrl, vehicle.vehicleUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vehicleUrl);
    }
}
