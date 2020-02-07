package number_theory;

public class ParameterResolve {


    public static String toString(boolean[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public String[] resolve(String s) {
        boolean quote = false;
        String[] params = new String[4];
        int cnt = 0;
        s += " ";
        StringBuilder sb = new StringBuilder();
        char prev= ' ';
        for(int i = 0; i <s.length(); i++) {
            char c = s.charAt(i);
            if(c == '"') {
                quote = !quote;
            }

            //没有引号且前置位空，开始下一个解析
            else if(!Character.isSpaceChar(prev) && Character.isSpaceChar(c) && !quote){
                if(sb.length() > 0) {
                    params[cnt++] = sb.toString();
                    sb = new StringBuilder();
                }
            }

            else if(!Character.isSpaceChar(c)  || (Character.isSpaceChar(c) && quote)){
                sb.append(c);
            }

            prev = c;
        }

        if(sb.length() > 0) {
            params[cnt] = sb.toString();
        }
        return params;
    }

    public static void main(String[] args) {
        for (String s : new ParameterResolve().resolve("xcopy /s    \"c:\\sdfd dfd\"  d:\\,")) {
            System.out.println(s);
        }
    }
}
