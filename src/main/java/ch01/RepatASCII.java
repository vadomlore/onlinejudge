package ch01;

//找出输入字符串中重复字符,根据ASCII码把重复字符从小到大输出
public class RepatASCII {

    //计数排序
    public static void printRepeatASCII(String s){
        byte[] charRepeatCounts = new byte[128];
        for (int i = 0; i < s.length(); i++) { //线性扫描s,对counts下标计数
            charRepeatCounts[(byte)s.charAt(i)]++;
        }
        for (int i = 0; i < charRepeatCounts.length; i++) { //扫描counts
            if(1 < charRepeatCounts[i]){
                System.out.printf("%v", (byte)i);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String s;
        while(!"".equals(s = IOUtil.getLine())){
            printRepeatASCII(s);
        }
    }

}
