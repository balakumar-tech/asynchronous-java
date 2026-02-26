package com.jp.lld.parkinglot;

import com.jp.lld.parkinglot.entities.ParkingFloor;
import com.jp.lld.parkinglot.entities.ParkingSpot;
import com.jp.lld.parkinglot.entities.ParkingTicket;
import com.jp.lld.parkinglot.strategy.fee.VehicleBasedFeeStrategy;
import com.jp.lld.parkinglot.vehicle.*;

import java.util.Optional;

public class ParkingLotDemo {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));


        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M3", VehicleSize.MEDIUM));



        ParkingFloor floor3 = new ParkingFloor(3);
        floor3.addSpot(new ParkingSpot("F3-S1", VehicleSize.SMALL));
        floor3.addSpot(new ParkingSpot("F3-S2", VehicleSize.SMALL));
        floor3.addSpot(new ParkingSpot("F3-S3", VehicleSize.SMALL));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        parkingLot.addFloor(floor3);
        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        System.out.println("Vehicle Entries");
        floor1.displayAvailability();
        floor2.displayAvailability();
        floor3.displayAvailability();


        Vehicle bike = new Bike("B-1");
        Vehicle car = new Car("C-1");
        Vehicle truck = new Truck("T-1");


        Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck);

        if(carTicket.isPresent()) {
            String licenseNumber = carTicket
                            .get().getVehicle().getLicenseNumber();
            Optional<Double> parkingFee =  parkingLot.unparkVehicle(licenseNumber);
            System.out.println("Fee for "+licenseNumber+": is $"+parkingFee);
        }

        /*if(truckTicket.isPresent()) {
            parkingLot.unparkVehicle(truckTicket.get().getVehicle().getLicenseNumber());
        }*/

        Vehicle t2 = new Truck("T-2");
        Optional<ParkingTicket> truck2 = parkingLot.parkVehicle(t2);

        System.out.println(truck2);
    }
}
