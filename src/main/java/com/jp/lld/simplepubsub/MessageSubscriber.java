package com.jp.lld.simplepubsub;

public class MessageSubscriber implements Subscriber {

    private String id;

    public MessageSubscriber(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void receiveMessage(Msg msg) {
        System.out.println("Id: "+id+" Message Received: "+msg);
    }
}
