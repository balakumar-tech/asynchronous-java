package com.jp.lld.pubsub.subscriber;

import com.jp.lld.pubsub.entities.Message;

public interface Subscriber {

    String getId();
    void onMessage(Message message);
}
