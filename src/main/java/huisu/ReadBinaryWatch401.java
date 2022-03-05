package huisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 401. 二进制手表
 *
 * @Author alan
 * @Date 2022/1/27 4:01 PM
 */
public class ReadBinaryWatch401 {

    public static void main(String[] args) {
        ReadBinaryWatch401 readBinaryWatch401 = new ReadBinaryWatch401();
        for (int i = 0; i < 10; i++) {
            readBinaryWatch401.f(i);
            //readBinaryWatch401.g(i);
        }
    }

    /**
     * 通过二叉决策树处理
     * @param count
     * @return
     */
    public List<String> f(int count) {
        // 确定好时分最大亮灯数量
        int maxHourBits = 3;
        int maxMinuteBits = 5;
        List<String> times = new ArrayList<>();
        if (count > maxHourBits + maxMinuteBits) {
            return times;
        }

        for (int i = 0; i <= maxHourBits; i++) {
            if (count - i > maxMinuteBits) {
                continue;
            }
            ArrayList<Integer> hours = new ArrayList<>();
            f(i, 4, hours);
            ArrayList<Integer> minutes = new ArrayList<>();
            f(count - i, 6, minutes);
            for (int j = 0; j < hours.size(); j++) {
                if (hours.get(j) >= 12 ) {
                    continue;
                }
                for (int k = 0; k < minutes.size(); k++) {
                    if (minutes.get(k) >= 60) {
                        continue;
                    }
                    String time = hours.get(j) +":"+ formatMinute(minutes.get(k));
                    times.add(time);
                    //System.out.println(time);
                }
            }
        }
        System.out.println("count:" + count + "==>" + times);
        return times;
    }

    public String formatMinute(int m) {
        if (m < 10) {
            return "0" + m;
        }
        return m + "";
    }

    /**
     * 计算在一定灯数量的情况下，指定亮灯数所有可能的值
     * @param need1
     * @param n
     * @param values
     */
    public void f(int need1, int n, List<Integer> values) {
        f(0, 0, need1, n, 0, 0, values);
    }

    public void f(int count1, int count0, int need1, int n, int index, int value, List<Integer> values) {
        if (count1 < need1) {
            f(count1 + 1, count0, need1, n, index+1, (int) (value + Math.pow(2, index)), values);
            if (count0 < n - need1) {
                f(count1, count0 + 1, need1, n, index+1, value, values);
            }
        }
        if (count1 == need1) {
            values.add(value);
            //System.out.println(value);
        }
    }

    /**
     * 提前计算好60以内的数对应的1个数
     * @param count
     * @return
     */
    public List<String> g(int count){
        int maxHourBits = 3;
        int maxMinuteBits = 5;
        List<String> times = new ArrayList<>();
        if (count > maxHourBits + maxMinuteBits) {
            return times;
        }
        Map<Integer, List<Integer>> hoursMap = gg(12);
        Map<Integer, List<Integer>> minutesMap = gg(60);
        for (int i = 0; i <= maxHourBits && i<=count; i++) {
            if (count - i > maxMinuteBits || count -i > count) {
                continue;
            }
            List<Integer> hours = hoursMap.get(i);
            List<Integer> minutes = minutesMap.get(count-i);
            for (int j = 0; j < hours.size(); j++) {
                if (hours.get(j) >= 12) {
                    continue;
                }
                for (int k = 0; k < minutes.size(); k++) {
                    if (minutes.get(k) >= 60) {
                        continue;
                    }
                    String time = hours.get(j) +":"+ formatMinute(minutes.get(k));
                    times.add(time);
                    //System.out.println(time);
                }
            }
        }
        System.out.println("count:" + count + "==>" + times);
        return times;
    }

    public Map<Integer, List<Integer>> gg(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count1 = gg1(i);
            if(!map.containsKey(count1)) {
                map.put(count1,new ArrayList<>());
            }
            map.get(count1).add(i);
        }
        return map;
    }

    public int gg1(int n) {
        if (n == 0) {
            return 0;
        }
        int count1 = 1;
        while ((n = n & (n - 1)) != 0) {
            count1++;
        }
        return count1;
    }
}
