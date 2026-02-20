package com.jp.lld.pubsub.entities;

import java.time.Instant;

public class Message {
    private final String payload;
    private final Instant timeStamp;

    public Message(String message) {
        this.payload = message;
        timeStamp = Instant.now();
    }

    public String getPayload() {
        return this.payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "payload='" + payload + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
