package com.jp.streams.gym;

public class Workout {

    private long id;
    private int startTime;
    private int endTime;

    public Workout(long id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getTotalTime() {
        return endTime - startTime;
    }
}
