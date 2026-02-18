package com.jp.streams.gym;

public class GymMembership {

    public static void main(String[] args) {
        MemberShip memberShip = new MemberShip();

        Member m1 = new Member(1,"Raj", MemberShipType.GOLD);
        Member m2 = new Member(2,"Mike", MemberShipType.SILVER);
        Member m3 = new Member(3,"Matt", MemberShipType.BRONZE);
        Member m4 = new Member(4,"Praveen", MemberShipType.GOLD);
        Member m5 = new Member(5,"Suresh", MemberShipType.SILVER);



        Workout w1 = new Workout(11,10,20);
        Workout w2 = new Workout(11,10,25);
        Workout w3 = new Workout(11,4,30);
        Workout w4 = new Workout(11,3,22);
        Workout w5 = new Workout(11,2,11);
        Workout w6 = new Workout(11,1,6);

        m1.addWorkout(w1);
        m1.addWorkout(w2);
        m1.addWorkout(w3);

        m2.addWorkout(w1);
        m2.addWorkout(w2);


        m3.addWorkout(w4);
        m4.addWorkout(w5);
        m5.addWorkout(w6);

        memberShip.addMember(m1);
        memberShip.addMember(m2);
        memberShip.addMember(m3);
        memberShip.addMember(m4);
        memberShip.addMember(m5);


        System.out.println(memberShip.toString());
        System.out.println(memberShip.getPaidCustomers().size());
        System.out.println(memberShip.getAverageWorkout());
    }
}
