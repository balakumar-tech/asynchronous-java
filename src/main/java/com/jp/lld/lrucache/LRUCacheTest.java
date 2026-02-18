package com.jp.lld.lrucache;

public class LRUCacheTest {

    public static void main(String[] args) {

        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("India", "Delhi");
        cache.put("Norway", "Oslo");
        cache.put("Nepal", "Katmandu");

        System.out.println(cache.get("India"));

        cache.put("France", "Paris");

        System.out.println(cache.get("Norway"));

    }
}
