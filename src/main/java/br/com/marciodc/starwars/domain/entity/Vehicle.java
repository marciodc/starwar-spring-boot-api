package br.com.marciodc.starwars.domain.entity;

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
}
