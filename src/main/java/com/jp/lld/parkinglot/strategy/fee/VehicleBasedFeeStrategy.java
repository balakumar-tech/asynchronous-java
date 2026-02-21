package com.jp.lld.parkinglot.strategy.fee;

import com.jp.lld.parkinglot.entities.ParkingTicket;
import com.jp.lld.parkinglot.vehicle.VehicleSize;

import java.util.HashMap;
import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    Map<VehicleSize, Double> HOURLY_RATES;
    public VehicleBasedFeeStrategy() {
        HOURLY_RATES = new HashMap<>();
        HOURLY_RATES.put(VehicleSize.SMALL, 1.0);
        HOURLY_RATES.put(VehicleSize.MEDIUM, 2.0);
        HOURLY_RATES.put(VehicleSize.LARGE, 5.0);
    }


    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExitTime() - parkingTicket.getEntryTime();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * HOURLY_RATES.get(parkingTicket.getVehicle().getSize());
    }
}
