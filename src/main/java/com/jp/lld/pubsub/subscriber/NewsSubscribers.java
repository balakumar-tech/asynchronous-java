package com.jp.lld.pubsub.subscriber;

import com.jp.lld.pubsub.entities.Message;

public class NewsSubscribers implements Subscriber {
    private final String id;

    public NewsSubscribers(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void onMessage(Message message) {
        System.out.printf("[Subscriber %s] received message '%s'%n", id, message.getPayload());
    }
}
