package com.jp.lld.simplepubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageBroker {

    BlockingQueue<Msg> queue;

    private Map<String, List<Subscriber>> topicSubscribers;

    public MessageBroker(int capacity) {
        this.queue  = new ArrayBlockingQueue<>(capacity);
        this.topicSubscribers = new HashMap<>();
        new Thread(this::dispatchMessage).start();
    }
    public void addToMessageQueue(Msg msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void subscribe(String topic, Subscriber subscriber) {

        this.topicSubscribers.computeIfAbsent(topic, k -> new ArrayList<>()).add(subscriber);
        System.out.println(subscriber.getId()+" subscribed to topic: "+topic);
    }

    public void unsubscribe(String topic, Subscriber subscriber) {

        if(this.topicSubscribers.containsKey(topic)) {
            this.topicSubscribers.get(topic).remove(subscriber);
        }

        System.out.println(subscriber.getId()+" subscribed to topic: "+topic);
    }

    public void dispatchMessage() {

        while(true) {
            try {
                Msg msg = queue.take();
                String topicId = msg.getTopic();
                if(topicSubscribers.containsKey(topicId)) {
                    for(Subscriber eachSubscriber: topicSubscribers.get(topicId)) {
                        eachSubscriber.receiveMessage(msg);
                    }
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
