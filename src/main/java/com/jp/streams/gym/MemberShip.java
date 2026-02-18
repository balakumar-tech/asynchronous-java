package com.jp.streams.gym;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemberShip {

    private List<Member> members;

    public MemberShip() {
        members = new ArrayList<>();
    }

    private final Predicate<Member> paidCustomer = (member) -> member.getMemberShipType() == MemberShipType.GOLD
            || member.getMemberShipType() == MemberShipType.SILVER;

    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public String toString() {
        return "MemberShip{" +
                "members=" + members +
                '}';
    }

    public List<Member> getPaidCustomers() {

        return members.stream().filter(paidCustomer)
                .collect(Collectors.toList());
    }

    public Map<Long, Double> getAverageWorkout() {

        return members.stream()
                .filter(member -> member.getWorkouts().size() > 0)
                .collect(Collectors.toMap(
                        Member::getMemberId,
                        member -> member.getWorkouts().stream()
                                .mapToLong(workout -> workout.getTotalTime())
                                .average().getAsDouble()));
    }
}
