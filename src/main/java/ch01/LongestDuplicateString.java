package ch01;


import java.util.Objects;

//重复字符最长串
//题目描述：给定一串字符，里面有些字符有连续出现的特点，请寻找这些连续出现字符中最长的串，
// 如果最长的串有多个，请输出字符ASCII码最小的那一串。
public class LongestDuplicateString {


    public static boolean asciiLess(char a, char b) {
        return a < b;
    }

    public static class Tuple<T1, T2>{
        private T1 t1;
        private T2 t2;

        private Tuple(){}


        public Tuple(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 t1() {
            return t1;
        }

        public T2 t2() {
            return t2;
        }

        public  static <T1, T2> Tuple<T1, T2> of(T1 t1, T2 t2){
            return new Tuple<>(t1, t2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple<?, ?> tuple = (Tuple<?, ?>) o;
            return Objects.equals(t1, tuple.t1) &&
                    Objects.equals(t2, tuple.t2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t1, t2);
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "t1=" + t1 +
                    ", t2=" + t2 +
                    '}';
        }
    }

    //(startIndex, endIndex)

    /**
     * [ )
     * @param s
     * @param startIndex include
     * @param endIndex exclude
     * @return first character index, second character length
     */
    static Tuple<Integer, Integer> maxDuplicateSubstrInASCIIOrder(String s, int startIndex, int endIndex) {
        if(s == null ||  "".equals(s))
            return Tuple.of(-1, 0);
        int maxIndex = -1;
        int maxCount = 0;
        assert startIndex <= endIndex && 0 <= startIndex && endIndex <= s.length();
        int thisIndex = startIndex;
        int thisCount = 1;
        for(int i = startIndex + 1; i <= endIndex; i++){
            if(i == endIndex || s.charAt(thisIndex) != s.charAt(i)){
                if(maxCount < thisCount || (maxCount == thisCount && asciiLess(s.charAt(thisIndex), s.charAt(maxIndex)))) {
                    maxCount = thisCount;
                    maxIndex = thisIndex;
                }
                thisCount = 1;
                thisIndex = i;
            }
            else {              // (s.charAt(thisIndex) == s.charAt(i)){
                thisCount++;
            }
        }
        return Tuple.of(maxIndex, maxCount);
    }

    public static void main(String[] args) {
        String s;
        while(!"".equals(s = IOUtil.getLine())){
//            Tuple<Integer, Integer> value = LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, s.length() -1);
//            System.out.printf("index:%d, char:%c, count:%d", value.t1(), s.charAt(value.t1()), value.t2());

            s = "cccbbb";
            Tuple<Integer, Integer>  value = LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 1, s.length());
            System.out.printf("index:%d, char:%c, count:%d", value.t1(), s.charAt(value.t1()), value.t2());

        }
    }
}
