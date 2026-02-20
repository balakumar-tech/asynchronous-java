package com.jp.lld.pubsub;

import com.jp.lld.pubsub.entities.Message;
import com.jp.lld.pubsub.subscriber.AlertSubscribers;
import com.jp.lld.pubsub.subscriber.NewsSubscribers;
import com.jp.lld.pubsub.subscriber.Subscriber;

public class PubSubDemo {

    public static void main(String[] args) {
        PubSubService instance = PubSubService.getInstance();

        final String NEWS = "NEWS";
        final String TECH = "TECH";
        final String WEATHER = "WEATHER";

        instance.createTopic(NEWS);
        instance.createTopic(TECH);
        instance.createTopic(WEATHER);

        Subscriber a1 = new AlertSubscribers("A1");
        Subscriber a2 = new AlertSubscribers("A2");
        Subscriber a3 = new AlertSubscribers("A3");
        Subscriber a4 = new AlertSubscribers("A4");
        Subscriber a5 = new AlertSubscribers("A5");
        Subscriber a6 = new AlertSubscribers("A6");


        instance.subscribe(TECH, a1);
        instance.subscribe(TECH, a2);
        instance.subscribe(TECH, a3);
        instance.subscribe(TECH, a4);
        instance.subscribe(TECH, a5);
        instance.subscribe(TECH, a6);


        Subscriber n1 = new NewsSubscribers("N1");
        Subscriber n2 = new NewsSubscribers("N2");
        Subscriber n3 = new NewsSubscribers("N3");
        Subscriber n4 = new NewsSubscribers("N4");
        Subscriber n5 = new NewsSubscribers("N5");
        Subscriber n6 = new NewsSubscribers("N6");


        instance.subscribe(NEWS, n1);
        instance.subscribe(NEWS, n2);
        instance.subscribe(NEWS, n3);
        instance.subscribe(NEWS, n4);
        instance.subscribe(NEWS, n5);
        instance.subscribe(NEWS, n6);

        Message m1 = new Message("High tariffs levied by Trump's government!");
        Message m2 = new Message("Tariffs to be challenged in Supreme court!");
        instance.publish(NEWS, m1);
        instance.publish(NEWS, m2);

        Message m3 = new Message("Server1 high on CPU!!!");
        Message m4 = new Message("Server2 High on memory!!!");

        instance.publish(TECH, m3);
        instance.publish(TECH, m4);

        instance.shutdown();

    }
}
