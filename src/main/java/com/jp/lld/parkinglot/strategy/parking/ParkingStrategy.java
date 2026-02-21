package com.jp.lld.parkinglot.strategy.parking;

import com.jp.lld.parkinglot.vehicle.Vehicle;
import com.jp.lld.parkinglot.entities.ParkingFloor;
import com.jp.lld.parkinglot.entities.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
