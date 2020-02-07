// 0 1 Knap 问题变体 ,增加具有父子关系物品情况的讨论 以及如何保障递归的单调性
package dp;

import java.util.Scanner;

public class Knap_01{

    public static class Item {

        public int no;

        public int v;

        public int p;

        public int q;

        public Item(int no, int v, int p, int q) {
            this.no = no;
            this.v = v;
            this.p = p;
            this.q = q;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "no=" + no +
                    ", v=" + v +
                    ", p=" + p +
                    ", q=" + q +
                    '}';
        }
    }

    public static int Knap(Item[] items, int money){
        Item[] stack = new Item[1000];
        return knap1(items, money, items.length - 1, stack, -1);
    }

    public static boolean contains(Item[] stack, int no, int top){
        if(top < 0) return false;
        for(int i = 0; i <= top; i++){
            if(stack[i].no == no){
                return true;
            }
        }
        return false;
    }



    // items表示所有物品
    // remainMoney 剩余金钱
    // n表示选择第几件
    // electedItems 表示已经选择的物品
    public static int knap1(Item[] items, int remainMoney, int n, Item[] stack, int top){
        if(n < 1) return 0;
        if(remainMoney < items[n].v) { //商品v太大以致于不可加入
            return knap1(items, remainMoney, n - 1, stack, top);
        }
        else if(contains(stack, n, top)) { //已加入的不再加入
            return knap1(items, remainMoney, n - 1, stack, top);
        }
        else{
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            v1 = knap1(items, remainMoney, n - 1, stack, top); //不选入该商品
            if(items[n].q == 0 &&!contains(stack, n, top)) { //该物品还没有被加入，且是主件
                stack[++top] = items[n];
                v2 = knap1(items, remainMoney - items[n].v, n - 1, stack, top) + items[n].v*items[n].p; //选入该商品
                top--;
            }
            else if(items[n].q != 0 && !contains(stack, n, top)) { //该物品还没有被加入，且是附件 --> 有2种选择，加入或者不加入,若加入则要保证主件能加入且将主件和附件一同加入
                v3 = knap1(items, remainMoney, n - 1, stack, top); //不加入的情况
                if(contains(stack, items[n].q, top)){ //主件已被加入
                    stack[++top] = items[n]; //加入该商品加入该物品
                    v3 = knap1(items, remainMoney - items[n].v, n - 1, stack, top) + items[n].v * items[n].p; //选入该商品
                    top--;
                } else{ //如果主键未加入
                    int parent = items[n].q;
                    int attach = items[n].v + items[parent].v;
                    if(attach <= remainMoney) {
                        stack[++top] = items[n];
                        stack[++top] = items[parent];
                        v4 = knap1(items, remainMoney - attach, n - 1, stack, top) + items[n].v * items[n].p + items[parent].v * items[parent].p; //主件未加入则将主件和附件一同纳入
                        --top;
                        --top;
                    }
                }
            }
            return Math.max(Math.max(v1, v2), Math.max(v3, v4));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String s = in.nextLine();
            String[] param = s.split(" ");
            int N = Integer.parseInt(param[0]);
            int m = Integer.parseInt(param[1]);
            Item[] items = new Item[m + 1];
            for(int i = 1; i <= m; i++){
                param = in.nextLine().split(" ");
                int v = Integer.parseInt(param[0]);
                int p = Integer.parseInt(param[1]);
                int q = Integer.parseInt(param[2]);
                items[i] = new Item(i, v, p, q);
            }
            System.out.println(Knap(items, N));
        }
    }
}