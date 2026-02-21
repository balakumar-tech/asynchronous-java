package com.jp.lld.parkinglot.vehicle;

public abstract class Vehicle {

    private final VehicleSize size;
    private final String licenseNumber;

    public Vehicle(String licenseNumber, VehicleSize size) {
        this.licenseNumber = licenseNumber;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
