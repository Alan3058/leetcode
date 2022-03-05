package huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * @Author alan
 * @Date 2022/2/6 5:08 PM
 */
public class BinaryTreePaths257 {

    public static void main(String[] args) {
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(6);
        TreeNode root = new TreeNode(10, left, right);
        left.left = new TreeNode(1);
        left.right = new TreeNode(4);
        right.right = new TreeNode(7);
        right.right.left = new TreeNode(8);
        right.right.right = new TreeNode(9);
        new BinaryTreePaths257().f(root);
    }

    public List<String> f(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        f(root, "", result);
        System.out.println(result);
        return result;
    }

    private void f(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path += root.val;
            result.add(path);
            return;
        }
        path = path + root.val + "->";
        if (root.left != null) {
            f(root.left, path, result);
        }
        if (root.right != null) {
            f(root.right, path, result);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
