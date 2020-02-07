package simle;

import java.util.*;

//人民币以4个位单位分组
public class ConvertRmb {
    public static Map<Integer, String> converterUnitTable = new HashMap();
    public static Map<Character, String> converterAmountTable = new HashMap();
    static {
        converterUnitTable.put(-2, "分");
        converterUnitTable.put(-1, "角");
        converterUnitTable.put(0, "");
        converterUnitTable.put(1, "万");
        converterUnitTable.put(2, "亿");
    }


    static {
        converterAmountTable.put('0', "零");
        converterAmountTable.put('1', "壹");
        converterAmountTable.put('2', "贰");
        converterAmountTable.put('3', "叁");
        converterAmountTable.put('4', "肆");
        converterAmountTable.put('5', "伍");
        converterAmountTable.put('6', "陆");
        converterAmountTable.put('7', "柒");
        converterAmountTable.put('8', "捌");
        converterAmountTable.put('9', "玖");
    }

    //获取当前在第几组
    public int getGroupByIndex(int i){
        return i / 4;
    }

    public boolean allZero(String value, int index){
        for(int i = index; i < value.length(); i++){
            if(value.charAt(i) != '0'){
                return false;
            }
        }
        return true;
    }


    public String toRmb(String rmb){
        boolean usedGroup = false;
        StringBuilder rmbStr = new StringBuilder();
        String prefixRmb = rmb.substring(0, rmb.indexOf(".") >= 0 ? rmb.indexOf(".") : rmb.length());
        String suffixRmb = "";
        if(rmb.contains(".")) {
            suffixRmb = rmb.substring(rmb.indexOf(".") + 1);
        }
        int n = prefixRmb.length();
        rmbStr.append("人民币");

        ArrayList<String> arr = new ArrayList<String>();
        String s = "";

        //将人民币前缀分组
        int pos = n - 1;
        int countDown = 4;
        while(0 < n--){
            s =  prefixRmb.charAt(pos--) + s;
            if(--countDown == 0 || n == 0){
                arr.add(0, s);
                s = "";
                countDown = 4;
            }
        }

        boolean hasLeadingZero = false;
        for(int i = 0; i < arr.size(); i++){
            String slice = arr.get(i);
            boolean allZero = allZero(slice, 1);
            while(slice.length() > 0){

                if(slice.length() == 4){
                    if(slice.charAt(0) != '0'){
                        rmbStr.append(converterAmountTable.get(slice.charAt(0)));
                        rmbStr.append("仟");
                        hasLeadingZero = false;
                    }else if(!hasLeadingZero){
                        if(!allZero) rmbStr.append("零");
                        hasLeadingZero = true;
                    }
                }
                if(slice.length() == 3){
                    if(slice.charAt(0) != '0'){
                        rmbStr.append(converterAmountTable.get(slice.charAt(0)));
                        rmbStr.append("佰");
                        hasLeadingZero = false;
                    }else if(!hasLeadingZero){
                        if(!allZero) rmbStr.append("零");
                        hasLeadingZero = true;
                    }
                }
                if(slice.length() == 2){
                    if(slice.charAt(0) != '0' ){
                        if(slice.charAt(0) != '1'){
                            rmbStr.append(converterAmountTable.get(slice.charAt(0)));
                            hasLeadingZero = false;
                        }
                        rmbStr.append("拾");
                    }else if(!hasLeadingZero){
                        if(!allZero)  rmbStr.append("零");
                        hasLeadingZero = true;
                    }
                }
                if(slice.length() == 1){
                    if(slice.charAt(0) != '0' ){
                        rmbStr.append(converterAmountTable.get(slice.charAt(0)));
                        hasLeadingZero = false;
                    }else if(!hasLeadingZero){
                        if(!allZero)  rmbStr.append("零");
                        hasLeadingZero = true;
                    }
                }
                slice = slice.substring(1);
            }
            int currentGroup = arr.size() - 1 - i;
            if(!usedGroup){
                rmbStr.append(converterUnitTable.get(currentGroup));
                usedGroup = true;
            }
        }
        if(!(prefixRmb.length() == 1 && prefixRmb.charAt(0) == '0')){
            rmbStr.append("元");
        }


        if(suffixRmb.equals("")){
            rmbStr.append("整");
        }
        else {
            boolean zero = true;
            for(int i = 0; i < suffixRmb.length(); i++){
                if(suffixRmb.charAt(i) != '0'){
                    zero = false;
                }
            }
            if(zero){
                rmbStr.append("整");
            }
        }
        if(suffixRmb.length() == 1){
            if(suffixRmb.charAt(0) != '0'){
                rmbStr.append(converterAmountTable.get(suffixRmb.charAt(0)));
                rmbStr.append(converterUnitTable.get(-1));
            }
        }

        if(suffixRmb.length() == 2){
            if(suffixRmb.charAt(0) != '0'){
                rmbStr.append(converterAmountTable.get(suffixRmb.charAt(0)));
                rmbStr.append(converterUnitTable.get(-1));
            }
            if(suffixRmb.charAt(1) != '0'){
                rmbStr.append(converterAmountTable.get(suffixRmb.charAt(1)));
                rmbStr.append(converterUnitTable.get(-2));
            }
        }
        return rmbStr.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            System.out.println(new ConvertRmb().toRmb(s));
        }
    }
}