package com.jp.lld.pubsub.subscriber;

import com.jp.lld.pubsub.entities.Message;

public class AlertSubscribers implements Subscriber {

    private final String id;

    public AlertSubscribers(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void onMessage(Message message) {
        System.out.printf("!!! [ALERT - %s] : '%s' !!!%n", id, message.getPayload());
    }
}
