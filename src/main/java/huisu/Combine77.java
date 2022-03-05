package huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * @Author alan
 * @Date 2022/2/6 6:34 PM
 */
public class Combine77 {

    public static void main(String[] args) {
        Combine77 combine77 = new Combine77();
        combine77.f(5, 3);
    }

    public List<List<Integer>> f(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            data.add(i);
        }
        // 1、特殊情况
        if (n < k) {
            return result;
        }
        if (n == k) {
            result.add(data);
            return result;
        }

        int level = 1;
        List<String> tmpResult = new ArrayList<>();
        // 2、递归处理
        f(data, k, level, "", tmpResult);

        // 3、将字符转数字
        for (String s : tmpResult) {
            String[] split = s.split(",");
            List<Integer> row = new ArrayList<>();
            for (String t : split) {
                row.add(Integer.valueOf(t));
            }
            result.add(row);
        }
        System.out.println(result);
        return result;
    }

    private void f(List<Integer> data, int k, int level, String path, List<String> tmpResult) {
        if (k < level) {
            tmpResult.add(path);
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            if (k == level) {
                f(data.subList(i+1, data.size()) , k, level + 1, path + data.get(i), tmpResult);
            } else {
                f(data.subList(i+1, data.size()) , k, level + 1, path + data.get(i) + ",", tmpResult);
            }
        }
    }
}
