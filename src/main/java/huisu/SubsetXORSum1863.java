package huisu;

/**
 * 1863. 找出所有子集的异或总和再求和
 * @Author alan
 * @Date 2022/1/27 12:04 PM
 */
public class SubsetXORSum1863 {

    public static void main(String[] args) {
        SubsetXORSum1863 subsetXORSum1863 = new SubsetXORSum1863();
        subsetXORSum1863.f(new int[]{1,2,3});
        subsetXORSum1863.f(new int[]{1,2,3,5,6,7,8,9});
        subsetXORSum1863.f(new int[]{7,6,2,9,5});
    }

    int sum = 0;

    public void f(int[] a) {
        System.out.println("所有子集：");
        f(a,0,0, new StringBuilder());
        System.out.println("所有子集抑或总和：" + sum);
    }

    /**
     * 二叉决策树方式，将每一层都当作是对一个数的选择，如选择（1）或不选择（0）
     * @param a
     * @param i
     * @param currentValue
     * @param sb
     */
    public void f(int[] a, int i, int currentValue, StringBuilder sb) {
        if (a.length == i) {
            sum += currentValue;
            System.out.println("》" + sb.toString() + "=" + currentValue);
            return;
        }
        f(a, i+1, a[i]^currentValue, new StringBuilder(sb).append(a[i]));
        f(a, i+1, 0^currentValue, sb);
    }
}
