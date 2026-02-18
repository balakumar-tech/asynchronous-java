package com.jp.lld.lrucache;

public class Node<K, V> {
    K key;
    V value;
    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
