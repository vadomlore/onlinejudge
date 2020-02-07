package collection;

import java.util.Vector;


/**
 * 线性探测再散列
 */
public class LinerHash {
    private static int mod = 5;

    private static void linerHash(Vector<Integer> v, int i, int value){

//        for(int j = i + 1, count = 0;   v.get(i) != null && v.get(i) != value && count < mod ; i = (i + 1) % mod, count++ );

        i = (i + 1) % mod;
        int count = 0;
        while(v.get(i) != null && v.get(i) != value && count < mod){
            i = (i + 1) % mod;
            count++;
        }
        if (v.get(i) == null) {
            v.set(i, value);
            return;
        }

        if(v.get(i) == value) return;

        throw new IndexOutOfBoundsException("hash table is full");
    }


    private Vector<Integer> table = new Vector<>(mod);

    public LinerHash(){
        for(int i = 0; i < mod; i++){
            table.add(null);
        }
    }

    private int hash(int key){
        return key % mod;
    }

    public void insert(int key) {
        int index = hash(key);
        if (table.get(index) == null) {
            table.set(index, key);
        }
        else {
            linerHash(table, index, key);
        }

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
        int count = 1;
        while((table.get(i) == null || table.get(i) != key) && count < mod){
            i = (i + 1) % mod;
            count++;
        }
        if(!(count < mod)){
            return -1;
        }
        if(table.get(i) == key){
            return i;
        }
        return -1;
    }

    public  boolean containsKey(int key){
        return find(key) != -1;
    }

    public boolean delete(int key) {
        int index;
        if((index = find(key)) != -1) {
            table.set(index, null);
            return true;
        }
        return false;
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
//        System.out.println(hashTable.table);
//
//        System.out.println(hashTable.delete(4));
//        System.out.println(hashTable.delete(4));

    }
}
