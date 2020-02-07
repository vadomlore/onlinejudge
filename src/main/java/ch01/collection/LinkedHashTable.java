package collection;


import java.util.LinkedList;
import java.util.Vector;


/**
 * 线性探测再散列
 */
public class LinkedHashTable {
    private static int mod = 5;
    private Vector<LinkedList<Integer>> table = new Vector<>(mod);


    public LinkedHashTable(){
        for(int i = 0; i < mod; i++){
            table.add(null);
        }
    }

    private int hash(int key){
        return key % mod;
    }

    public void insert(int key) {
        int index = hash(key);
        LinkedList<Integer> ls;
        if ((ls = table.get(index)) == null) {
            ls = new LinkedList<>();
            table.set(index, ls);
        }
        if(!ls.contains(key)) ls.add(key);
    }

    /**
     * 如何不漏掉条件判断;
     * while 中针对a&&b&&c 的每一个条件，出口处要判断每个 !a; !b; !c
     *
     * while(a&&b) 表示a b都为true才进循环 且有短路运算先支持a
     *
     * while(a || b) 表示a b 至少有一个为true 就可以进入循环
     * @param key
     * @return
     */
    public int find(int key){
        int i = hash(key);
        LinkedList<Integer> ls = table.get(i);
        if (ls == null) {
            return -1;
        }
        return ls.contains(key) ? i: -1;
    }

    public  boolean containsKey(int key){
        return find(key) != -1;
    }

    public boolean delete(int key) {
        int i = hash(key);
        LinkedList<Integer> ls = table.get(i);
        if (ls == null) {
            return false;
        }

        return ls.remove((Integer)key);
    }

    public static void main(String[] args) {
        LinkedHashTable hashTable = new LinkedHashTable();
        hashTable.insert(4);
        hashTable.insert(9);
        hashTable.insert(9);
        hashTable.insert(12);

        hashTable.insert(11);

        System.out.println(hashTable.containsKey(4));
        System.out.println(hashTable.containsKey(9));
        System.out.println(hashTable.containsKey(11));
        System.out.println(hashTable.containsKey(12));
        System.out.println(hashTable.containsKey(16));

//        System.out.println(hashTable.find(11));
//        System.out.println(hashTable.find(1));
//
        System.out.println(hashTable.table);

        System.out.println(hashTable.delete(4));
        System.out.println(hashTable.delete(4));

    }
}

