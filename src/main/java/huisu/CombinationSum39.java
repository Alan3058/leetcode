package huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 *
 * @Author alan
 * @Date 2022/2/7 2:15 PM
 */
public class CombinationSum39 {

    public static void main(String[] args) {
        CombinationSum39 combinationSum39 = new CombinationSum39();
        combinationSum39.f(new int[]{1, 2, 3, 8}, 10);
    }

    public List<List<Integer>> f(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> tmp = new ArrayList<>();
        f(candidates, target, result, tmp, 0);
        System.out.println("size:" + result.size());
        System.out.println(result);
        return result;
    }

    private void f(int[] candidates, int target, List<List<Integer>> result, List<Integer> tmp, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        if (index >= candidates.length) {
            return;
        }

        if (target >= candidates[index]) {
            // 1、跳过当前点
            f(candidates, target, result, tmp, index + 1);
            // 2、使用当前点
            tmp.add(candidates[index]);
            // 尝试继续使用当前点
            f(candidates, target - candidates[index], result, tmp, index);
            tmp.remove(tmp.size() - 1);
        }
    }
}
