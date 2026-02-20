package com.jp.lld.pubsub;

import com.jp.lld.pubsub.entities.Message;
import com.jp.lld.pubsub.entities.Topic;
import com.jp.lld.pubsub.subscriber.Subscriber;

import java.sql.Time;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubSubService {

    private static final PubSubService INSTANCE = new PubSubService();
    private final ExecutorService deliveryExecutor;
    private final Map<String, Topic> topicRegistry;

    private PubSubService() {
        this.topicRegistry = new ConcurrentHashMap<>();
        this.deliveryExecutor = Executors.newCachedThreadPool();
    }

    public static PubSubService getInstance() {
        return INSTANCE;
    }

    public void createTopic(String topicName) {
        topicRegistry.putIfAbsent(topicName, new Topic(topicName, deliveryExecutor) );
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if(topic == null) {
            throw new IllegalArgumentException("Topic not found: "+topicName);
        }

        topic.addSubscriber(subscriber);
        System.out.println("Subscriber "+subscriber.getId()+" subscribed to topic "+topicName);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if(topic !=  null) {
            topic.removeSubscriber(subscriber);
        }

        System.out.println("Subscriber "+subscriber.getId()+" removed from topic: "+topicName);
    }

    public void publish(String topicName, Message message) {
        Topic topic = topicRegistry.get(topicName);
        topic.broadcast(message);
    }

    public void shutdown() {

        deliveryExecutor.shutdown();
        try {
            if(!deliveryExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Given one minute to complete the message delivery!!!");
                deliveryExecutor.shutdownNow();
            }
        } catch(InterruptedException e) {
            deliveryExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("PubSubService shutdown complete");
    }
}
