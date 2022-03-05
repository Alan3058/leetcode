package huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 *
 * @Author alan
 * @Date 2022/1/28 4:55 PM
 */
public class RestoreIpAddresses93 {

    public static void main(String[] args) {
        RestoreIpAddresses93 t = new RestoreIpAddresses93();
        t.f("00000");
        t.f("1234");
        t.f("01234");
        t.f("018170255");
    }

    public List<String> f(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return list;
        }
        List<Data> datas = new ArrayList<>();
        // 提取出多叉树（递归+回溯）
        for (int i = 0; i < 3; i++) {
            String value = s.substring(0, i + 1);
            Data first = new Data(Integer.parseInt(value));
            if (first.cur > 255) {
                break;
            }
            if (f(s, i + 1, 2, first)) {
                datas.add(first);
            }
            if ("0".equals(value)) {
                break;
            }
        }
        // 从多叉树提取所有到叶子节点的路径，即为ip地址（递归+回溯）
        list = extract(datas, "");
        System.out.println(s + ":" + list);
        return list;
    }

    private List<String> extract(List<Data> datas, String pre) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            Data data = datas.get(i);
            if (data.next == null || data.next.isEmpty()) {
                list.add(pre + "." + data.cur);
            } else {
                list.addAll(extract(data.next, pre.isEmpty() ? data.cur + "" : pre + "." + data.cur));
            }
        }
        return list;
    }

    public boolean f(String s, int index, int level, Data pre) {
        if ((level > 4 && index < s.length())) {
            return false;
        }
        if ((level > 4 && index == s.length())) {
            return true;
        }
        boolean flag = false;
        for (int i = index; i < index + 3 && i < s.length(); i++) {
            String value = s.substring(index, i + 1);
            Data data = new Data(Integer.parseInt(value));
            if (data.cur > 255) {
                break;
            }
            if (f(s, i + 1, level + 1, data)) {
                pre.next.add(data);
                flag = true;
            }
            if ("0".equals(value)) {
                break;
            }
        }
        return flag;
    }

    private static class Data {

        public Data(int cur) {
            this.cur = cur;
        }

        private int cur;
        private List<Data> next = new ArrayList<>();
    }
}
