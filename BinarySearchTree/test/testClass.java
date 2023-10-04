import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PA5Test {

    @Test
    public void nit_size() {
        BinSearchTree<String> bst = new BinSearchTree<String>();
        // assert statements
        assertTrue(bst.isEmpty());
        assertTrue(bst.root() == null);
    }

    @Test
    public void test_add_correct_order(){
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = { "g", "c", "k", "u", "a", "w", "e", "b" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }
        Arrays.sort(words);
        int index = 0;
        for(String s : bst)
        {
            assertEquals(s, words[index]);
            index+=1;
        }
    }



    @Test
    public void test_remove_correct_order(){
        BinSearchTree<String> bst = new BinSearchTree<String>();

        String[] words = {  "a", "g", "k", "w", "e", "b","u" , "c" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }

        Arrays.sort(words);

        int index = 0;
        for(String s : bst)
        {
            assertEquals(s, words[index]);
            index++;
        }

        bst.remove("c");
        String[] words2 = {  "a", "g", "k", "w", "e", "b","u"  };
        Arrays.sort(words2);

        index = 0;
        for(String s : bst)
        {
            assertEquals(s, words2[index]);
            index++;
        }


    }

    @Test
    public void remove_empty() {
        BinSearchTree<String> bst = new BinSearchTree<String>();

        bst.add("A");
        assertTrue(bst.root().element() == "A");

        bst.remove("A");
        assertTrue(bst.root() == null);

    }

}
