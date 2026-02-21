package com.jp.lld.parkinglot.entities;

import com.jp.lld.parkinglot.vehicle.Vehicle;
import com.jp.lld.parkinglot.vehicle.VehicleSize;

public class ParkingSpot {

    private final VehicleSize spotSize;
    private final String spotId;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        isOccupied = false;
        parkedVehicle = null;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if(isOccupied) return false;
        switch(vehicle.getSize()) {
            case SMALL:
                return spotSize == VehicleSize.SMALL;
            case MEDIUM:
                    return spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;
            case LARGE:
                return spotSize == VehicleSize.LARGE;
            default:
                return false;
        }
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        isOccupied = true;
        parkedVehicle = vehicle;
    }

    public synchronized void unParkVehicle() {
        isOccupied = false;
        parkedVehicle = null;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }
}
