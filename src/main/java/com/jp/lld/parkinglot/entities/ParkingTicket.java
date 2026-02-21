package com.jp.lld.parkinglot.entities;

import com.jp.lld.parkinglot.vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;

    private long entryTime;
    private  long exitTime;

    public ParkingTicket(ParkingSpot spot, Vehicle vehicle) {
        this.spot = spot;
        this.ticketId = UUID.randomUUID().toString();
        this.entryTime = new Date().getTime();
        this.vehicle = vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setExitTime() {
        this.exitTime = new Date().getTime();
    }


}
