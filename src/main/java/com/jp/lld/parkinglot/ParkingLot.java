package com.jp.lld.parkinglot;

import com.jp.lld.parkinglot.entities.ParkingFloor;
import com.jp.lld.parkinglot.entities.ParkingTicket;
import com.jp.lld.parkinglot.strategy.fee.FeeStrategy;
import com.jp.lld.parkinglot.strategy.fee.FlatRateFeeStrategy;
import com.jp.lld.parkinglot.strategy.parking.BestFitStrategy;
import com.jp.lld.parkinglot.strategy.parking.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;
    private ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStrategy = new BestFitStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }
}
