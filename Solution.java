mport java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer target = sc.nextInt();

        sc = new Scanner(System.in);
        String arrayStr = sc.nextLine();
        int[] array = Unit.StringToInt(arrayStr.split(","));

        Tree tree = new Tree(array);
        tree.computePaths(target);
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class Tree{
    private TreeNode root;
    private TreeNode[] nodes;

    public Tree(int[] array){
        int len = array.length;
        nodes = new TreeNode[len];

        //构建二叉树
        for (int i = 0; i < len; i++) {
            nodes[i] = new TreeNode(array[i]);
        }

        root = nodes[0];
        for (int i = 0; i < len; i++) {
            int leftIndex = i * 2 + 1;
            if (leftIndex < len) {
                nodes[i].left = nodes[leftIndex];
            }

            int rightIndex = i * 2 + 2;
            if (rightIndex < len) {
                nodes[i].right = nodes[rightIndex];
            }
        }
    }

    public void computePaths(int target) {
        printTree(root);
        printPaths(root, target);
    }

    private void printPaths(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        int sum = 0;

        printPaths(root, sum, target, arrayList);
    }

    private void printPaths(TreeNode root, int sum, int target, List<Integer> arrayList) {
        sum += root.val;
        arrayList.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == target) {
                System.out.println(Unit.arrayToString(arrayList));
            }
        }

        // 如果大于target直接就可以退出计算了
        if (sum < target) {
            if (root.left != null) {
                printPaths(root.left, sum, target, arrayList);
            }
            if (root.right != null) {
                printPaths(root.right, sum, target, arrayList);
            }
        }

        arrayList.remove(arrayList.size() - 1);
    }

    public void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        if (root.left != null) {
            printTree(root.left);
        }

        if (root.right != null) {
            printTree(root.right);
        }
    }

    public TreeNode getRoot() {
        return root;
    }
}

class Unit{
    public static int[] StringToInt(String[] strs) {
        int[] ints = new int[strs.length];

        for (int i = 0; i < strs.length; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }

        return ints;
    }

    public static String arrayToString(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
