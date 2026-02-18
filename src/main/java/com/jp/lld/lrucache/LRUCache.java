package com.jp.lld.lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K,V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        dll = new DoublyLinkedList<>();
    }

    public synchronized V get(K key) {
        if(!map.containsKey(key)) {
            return null;
        }
        Node<K, V> node = map.get(key);
        dll.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        if(map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            dll.moveToFront(node);
            map.put(key, node);
        } else {
            if(capacity == map.size()) {
                Node<K, V> lru = dll.removelast();
                map.remove(lru.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    public synchronized void remove(K key) {
        if(!map.containsKey(key)) return;
        Node<K, V> node = map.get(key);
        dll.remove(node);
        map.remove(key);
    }
}
