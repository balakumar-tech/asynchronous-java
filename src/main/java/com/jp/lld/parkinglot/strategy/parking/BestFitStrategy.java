package com.jp.lld.parkinglot.strategy.parking;

import com.jp.lld.parkinglot.vehicle.Vehicle;
import com.jp.lld.parkinglot.entities.ParkingFloor;
import com.jp.lld.parkinglot.entities.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        Optional<ParkingSpot> bestSpot = Optional.empty();
        for(ParkingFloor parkingFloor: parkingFloors) {
            Optional<ParkingSpot> spotOnthisFloor = parkingFloor.findParkingSpot(vehicle);
            if(spotOnthisFloor.isPresent()) {
                if(bestSpot.isEmpty()) {
                    bestSpot = spotOnthisFloor;
                } else {
                    if(spotOnthisFloor.get().getSpotSize().ordinal() <
                            bestSpot .get().getSpotSize().ordinal()){
                        bestSpot = spotOnthisFloor;
                    }
                }
            }
        }
        return bestSpot;
    }
}
