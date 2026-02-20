package com.jp.lld.simplepubsub;

public class TestSimplePubSub {

    public static void main(String[] args) {
        MessageBroker broker = new MessageBroker(1);

        Subscriber s1 = new SimpleSubscriber("T1");
        Subscriber s2 = new SimpleSubscriber("T2");
        Subscriber s3 = new SimpleSubscriber("T3");

        broker.subscribe("NEWS", s1);
        broker.subscribe("TECH", s2);
        broker.subscribe("NEWS", s3);


        Publisher pub = new Publisher(broker);
        pub.publish(new Msg("NEWS", "New NEWS 1"));
        pub.publish(new Msg("NEWS", "New NEWS 2"));

        pub.publish(new Msg("TECH", "TECH 1"));
    }
}

class SimpleSubscriber implements Subscriber {

    final String id;

    public SimpleSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void receiveMessage(Msg msg) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message received at SimpleSubscriber: "+id+"| Message -----> "+msg);
    }
}
