import org.junit.Test;
import static org.junit.Assert.*;

public class TestAVLTree {

    // int
    @Test
    public void test_int_rebalance() {
        AVLTree<Integer> avl = new AVLTree();
        AVLTreeNode<Integer> root = new AVLTreeNode(3, null, null, null);
        avl.setRoot(root);
        AVLTreeNode<Integer> left1 = new AVLTreeNode(2, root, null, null);
        root.setLeft(left1);
        AVLTreeNode<Integer> left2 = new AVLTreeNode(1, left1, null, null);
        left1.setLeft(left2);
        root.updateHeight();
        left1.updateHeight();
        avl.rebalance(root);
        assertEquals("AVLRoot: \n" +
                "  Element: 2;  Height: 1\n" +
                "  Left:\n" +
                "    Element: 1;  Height: 0\n" +
                "  Right:\n" +
                "    Element: 3;  Height: 0\n", avl.toString());
    }

    @Test
    public void test_int_add() {
        AVLTree<Integer> avl = new AVLTree();
        int[] nums = {1, 0};
        for(int num: nums) {
            avl.add(num);
        }
        assertEquals("AVLRoot: \n" +
                "  Element: 1;  Height: 1\n" +
                "  Left:\n" +
                "    Element: 0;  Height: 0\n", avl.toString());
    }

    // String
    @Test
    public void test_string_rebalance() {
        AVLTree<String> avl = new AVLTree();
        AVLTreeNode<String> root = new AVLTreeNode("c", null, null, null);
        avl.setRoot(root);
        AVLTreeNode<String> left1 = new AVLTreeNode("b", root, null, null);
        root.setLeft(left1);
        AVLTreeNode<String> left2 = new AVLTreeNode("a", left1, null, null);
        left1.setLeft(left2);
        root.updateHeight();
        left1.updateHeight();
        avl.rebalance(root);
        assertEquals("AVLRoot: \n" +
                "  Element: b;  Height: 1\n" +
                "  Left:\n" +
                "    Element: a;  Height: 0\n" +
                "  Right:\n" +
                "    Element: c;  Height: 0\n", avl.toString());
    }

    @Test
    public void test_string_add() {
        AVLTree<String> avl = new AVLTree();
        String[] string = {"b", "a"};
        for(String s: string) {
            avl.add(s);
        }
        assertEquals("AVLRoot: \n" +
                "  Element: b;  Height: 1\n" +
                "  Left:\n" +
                "    Element: a;  Height: 0\n", avl.toString());
    }
}