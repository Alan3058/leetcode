package huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 1849. 将字符串拆分为递减的连续值
 * @Author alan
 * @Date 2022/1/28 4:47 PM
 */
public class SplitString1849 {

    public static void main(String[] args) {
        SplitString1849 t = new SplitString1849();
//        t.f("4321");
//        t.f("40321");
//        t.f("04003211");
//        t.f("10009998");
//        t.f("9080701");
//        t.f("050043");
//        t.f("0568805687");
        t.f("109111111122222222299");
        t.f("200100");
    }

    public boolean f(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            Long first = Long.valueOf(s.substring(0, i + 1));
            if (first >= Math.pow(10,10)){
                break;
            }
            if (f(first, s, i+1)) {
                System.out.println(s + ":true");
                return true;
            }
        }
        System.out.println(s + ":false");
        return false;
    }

    public boolean f(Long pre, String s, int index) {
        boolean flag = true;
        for (int i = index; i < s.length(); i++) {
            Long current = Long.valueOf(s.substring(index, i + 1));
            if (current + 1 > pre || current >= Math.pow(10,10)) {
                // 只超过10的10次活着当前值大于前面的值，直接不匹配，跳出循环
                return false;
            }
            if (current + 1 < pre) {
                // 当前值不等于前面的值，继续循环
                flag = false;
                continue;
            }
            if (pre == 1) {
                // 特殊情况，1，之后可以有多个0
                return f(pre, s, i+1);
            }
            // 值相等直接递归
            return f(current, s, i+1);
        }
        return flag;
    }

}
