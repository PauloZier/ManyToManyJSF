package net.ddns.zierservices.util;

public interface Pair <K, V> {

    void put(K key, V value);
    K getKey();
    V getValue();

}
