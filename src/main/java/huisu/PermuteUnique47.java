package huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 47. 全排列 II
 *
 * @Author alan
 * @Date 2022/2/11 6:36 PM
 */
public class PermuteUnique47 {

    public static void main(String[] args) {
        System.out.println(new PermuteUnique47().f(new int[]{1, 2, 1,3}));
    }

    public List<List<Integer>> f(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        List<Integer> data = Arrays.stream(nums).sorted().mapToObj(i -> i).collect(Collectors.toList());
        f(result, tmp, data, nums.length);
        return result;
    }

    private void f(List<List<Integer>> result, List<Integer> tmp, List<Integer> data, int length) {
        if (tmp.size() == length) {
            // 遍历完一条路径，将临时结果集数据加入到结果集
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < data.size(); i++) {
            if (i + 1 < data.size() && data.get(i).equals(data.get(i+1))) {
                // 去重
                continue;
            }
            // 1、先将数据移除，并将数据添加到临时结果集中
            Integer current = data.remove(i);
            tmp.add(current);
            // 2、递归树下一层级
            f(result, tmp, data, length);
            // 3、回溯，移除临时结果集的顶部数据，并将移除的数据加到原始数据中
            tmp.remove(tmp.size() - 1);
            data.add(i, current);
        }
    }
}