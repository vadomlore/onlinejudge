package simle;

import java.util.*;

public class UnionRecord {

    public static class TupleInt {
        int t1;
        int t2;
        public TupleInt(int t1, int t2){
            this.t1 = t1;
            this.t2 = t2;
        }

        public int t1(){
            return t1;
        }

        public int t2(){
            return t2;
        }

        @Override
        public String toString(){
            return String.valueOf(t1) + " " + String.valueOf(t2);
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Stack<TupleInt> resultStack = new Stack<>();
        Stack<TupleInt> tempStack = new Stack<>();

        while(in.hasNext()){
            int n = Integer.parseInt(in.nextLine());
            for(int i = 0; i < n; i++){
                String raw = in.nextLine();
                String[] st = raw.split(" ");
                int key = Integer.parseInt(st[0]);
                int value = Integer.parseInt(st[1]);
                TupleInt t = new TupleInt(key, value);
                if(resultStack.isEmpty() || t.t1() < resultStack.peek().t1()){
                    resultStack.push(t);
                }
                else{
                    TupleInt top = resultStack.peek();
                    if(top.t1() == t.t1()){
                        resultStack.push(new TupleInt(top.t1(), resultStack.pop().t2() + t.t2()));
                    }
                    else{
                        while(!resultStack.isEmpty()&& top.t1() < t.t1()){
                            tempStack.push(resultStack.pop());
                            if(resultStack.empty()){
                                break;
                            }
                            top = resultStack.peek();
                        }
                        if(top.t1() == t.t1()){
                            resultStack.push(new TupleInt(top.t1(), resultStack.pop().t2() + t.t2()));
                        }else{
                            resultStack.push(t);
                        }
                        while(!tempStack.isEmpty()){
                            resultStack.push(tempStack.pop());
                        }
                    }
                }
            }
            while(!resultStack.isEmpty()){
                System.out.println(resultStack.pop());
            }
        }
    }
}