package huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * @Author alan
 * @Date 2022/2/7 2:15 PM
 */
public class CombinationSum40 {

    public static void main(String[] args) {
        CombinationSum40 combinationSum40 = new CombinationSum40();
        combinationSum40.f(new int[]{1, 1, 2,1, 3, 8, 6, 7}, 10);
    }

    public List<List<Integer>> f(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> tmp = new ArrayList<>();
        f(candidates, target, result, tmp, 0);
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
            // 1、跳过当前点，并找到下一个点和当前点不相等的点
            for (int i = index + 1; i < candidates.length; i++) {
                if (candidates[index] != candidates[i]) {
                    f(candidates, target, result, tmp, i);
                    break;
                }
            }

            // 2、使用当前点
            tmp.add(candidates[index]);
            // 尝试继续使用当前点
            f(candidates, target - candidates[index], result, tmp, index + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
