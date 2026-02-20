package com.jp.lld.simplepubsub;

public interface Subscriber {
    String getId();
    void receiveMessage(Msg msg);

}
