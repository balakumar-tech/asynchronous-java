package com.jp.lld.simplepubsub;

public class Publisher {

    private MessageBroker messageBroker;

    public Publisher(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void publish(Msg msg) {
        System.out.println("Publish action received for "+msg);
        this.messageBroker.addToMessageQueue(msg);
    }
}
