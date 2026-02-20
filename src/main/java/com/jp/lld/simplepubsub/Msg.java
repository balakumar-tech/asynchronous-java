package com.jp.lld.simplepubsub;

public class Msg {

    private String topic;
    private String content;

    public Msg(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
