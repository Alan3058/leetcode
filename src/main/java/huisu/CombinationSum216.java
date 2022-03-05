package huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 *
 * @Author alan
 * @Date 2022/2/7 2:15 PM
 */
public class CombinationSum216 {

    public static void main(String[] args) {
        CombinationSum216 combinationSum216 = new CombinationSum216();
        System.out.println(combinationSum216.f(3, 10));
        System.out.println(combinationSum216.f(2, 19));
    }

    public List<List<Integer>> f(int k, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        f(k, target, result, tmp, 1);
        return result;
    }

    private void f(int k, int target, List<List<Integer>> result, List<Integer> tmp, int current) {
        if (target == 0 && tmp.size() == k) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        if (tmp.size() > k || current > 9 || current > target) {
            return;
        }

        if (target >= current) {
            // 1、跳过当前点
            f(k, target, result, tmp, current + 1);

            // 2、使用当前点
            tmp.add(current);
            // 尝试继续使用当前点
            f(k, target - current, result, tmp, current + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
