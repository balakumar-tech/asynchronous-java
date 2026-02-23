package com.jp.lld.parkinglot;

import com.jp.lld.parkinglot.entities.ParkingFloor;
import com.jp.lld.parkinglot.entities.ParkingSpot;
import com.jp.lld.parkinglot.entities.ParkingTicket;
import com.jp.lld.parkinglot.strategy.fee.FeeStrategy;
import com.jp.lld.parkinglot.strategy.fee.FlatRateFeeStrategy;
import com.jp.lld.parkinglot.strategy.parking.BestFitStrategy;
import com.jp.lld.parkinglot.strategy.parking.ParkingStrategy;
import com.jp.lld.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public static synchronized ParkingLot getInstance() {
        if(instance  == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(floors, vehicle);

        if(availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);

            ParkingTicket parkingTicket = new ParkingTicket(spot, vehicle);
            activeTickets.put(vehicle.getLicenseNumber(), parkingTicket);

            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(),
                    spot.getSpotId(), parkingTicket.getTicketId());
            return Optional.of(parkingTicket);

        }

        System.out.println("Not available spot for vehicle:"+vehicle.getLicenseNumber());
        return Optional.empty();
    }


    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.get(licenseNumber);

        if(ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExitTime();
        ticket.getSpot().unParkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}
