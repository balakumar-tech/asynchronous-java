package com.jp.lld.lrucache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCacheConcurrent<K, V> {

    private final int capacity;
    private final ConcurrentHashMap<K, Node<K, V>> map;
    private final DoublyLinkedList<K,V> dll;
    private final ReentrantLock[] stripes;
    private final int stripeCount;

    public LRUCacheConcurrent(int capacity, int stripeCount) {
        this.capacity = capacity;
        this.stripeCount = stripeCount;
        map = new ConcurrentHashMap<>();
        dll = new DoublyLinkedList<>();
        stripes = new ReentrantLock[stripeCount];
        for(int i=0; i< stripeCount; i++) {
            stripes[i] = new ReentrantLock();
        }
    }

    private ReentrantLock getLockForKey(K key) {
        int index = Math.abs(key.hashCode() % stripeCount);
        return stripes[index];
    }

    public V get(K key) {
        ReentrantLock lock = getLockForKey(key);
        lock.lock();
        try {
            if(!map.contains(key)) return null;
            Node<K, V> node = map.get(key);
            dll.moveToFront(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        ReentrantLock lock = getLockForKey(key);
        lock.lock();
        try {
            if(map.contains(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                dll.moveToFront(node);
            } else {
                Node<K, V> newNode = new Node<>(key, value);
                if(map.size() >= capacity) {
                    evict();
                }
                dll.addFirst(newNode);
                map.put(key, newNode);
            }
        } finally {
            lock.unlock();
        }
    }

    public void remove(K key) {
        ReentrantLock lock = getLockForKey(key);
        lock.lock();
        try {
            if(!map.containsKey(key)) return;
            Node<K, V> node = map.get(key);
            dll.remove(node);
            map.remove(key);
        } finally {
            lock.unlock();
        }
    }

    private void evict() {
        for(ReentrantLock lock: stripes) {
            lock.lock();
        }

        try {
            if(map.size() >= capacity) {
                Node<K, V> last = dll.removelast();
                if(last != null) {
                    map.remove(last.key);
                }
            }
        } finally {
            for(ReentrantLock lock: stripes) {
                lock.unlock();
            }
        }
    }

}
