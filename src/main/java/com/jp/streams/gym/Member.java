package com.jp.streams.gym;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private long memberId;
    private String memberName;
    private MemberShipType memberShipType;

    private List<Workout> workouts;
    public Member(long memberId, String memberName, MemberShipType memberShipType) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberShipType = memberShipType;
        this.workouts = new ArrayList<>();
    }

    public long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public MemberShipType getMemberShipType() {
        return memberShipType;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberShipType=" + memberShipType +
                ", workout=" + workouts +
                '}';
    }
}