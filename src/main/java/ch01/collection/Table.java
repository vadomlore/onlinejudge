package collection;

import java.util.Objects;

/**
 * 数字盒子
 * 集合
 * insert
 * delete
 */
public class Table<K, V> {
    int MAX_SIZE = 1000000;

    public class Pair<K, V> {
        K key;
        V value;
    }

    public int hash(K k){
        return Objects.hash(k) % MAX_SIZE;
    }



}
