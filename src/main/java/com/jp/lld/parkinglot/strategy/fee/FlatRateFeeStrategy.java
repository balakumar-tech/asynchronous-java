package com.jp.lld.parkinglot.strategy.fee;

import com.jp.lld.parkinglot.entities.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {
    double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExitTime() - parkingTicket.getEntryTime();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * RATE_PER_HOUR;
    }
}
