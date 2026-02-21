package com.jp.lld.parkinglot.strategy.fee;

import com.jp.lld.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
