import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestClass {

    CircularLinkedList intListTester;
    CircularLinkedList stringListTester;
    public void init(){
        intListTester = new CircularLinkedList();
        stringListTester = new CircularLinkedList<String>();
    }


    //Circular Linked List tests start from here
    @Test
    public void testListString_getItem()
    {
        init();
        assertEquals(null, stringListTester.getFirst());
        assertEquals(null, stringListTester.getLast());
    }
    @Test
    public void testListString_addFirst()
    {
        init();
        stringListTester.addLast("prius");
        assertEquals("prius", stringListTester.getFirst());
        stringListTester.addFirst("ding");
        assertEquals("ding", stringListTester.getFirst());
    }


    @Test
    public void testListString_getSize()
    {
        init();
        assertEquals(0, stringListTester.getSize());
        stringListTester.addLast("prius");
        assertEquals(1, stringListTester.getSize());
        stringListTester.addLast("ding");
        assertEquals(2, stringListTester.getSize());
    }
    @Test
    public void testListString_removeFirst()
    {
        init();
        assertEquals(null, stringListTester.removeFirst());
        String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};

        for (String i: cars)
            stringListTester.addLast(i);

        for (String i: cars)
            assertEquals(i ,stringListTester.removeFirst());
    }


    @Test
    public void testListString_rotate()
    {
        init();

        String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};
        for (String i: cars)
            stringListTester.addLast(i);

        String[] cars_rotated = { "rav4", "subaru", "crv", "pilot", "prius"};
        stringListTester.rotate();
        for (String i: cars_rotated){
            assertEquals(i, stringListTester.removeFirst());
        }

    }

    @Test
    public void testListInt_isEmpty()
    {
        init();

        assertEquals(true ,intListTester.isEmpty());
        intListTester.addLast(1);
        assertEquals(false ,intListTester.isEmpty());
    }


    @Test
    public void testListInt_getItem()
    {
        init();
        assertEquals(null, intListTester.getFirst());
        assertEquals(null, intListTester.getLast());
    }
    @Test
    public void testListInt_addFirst()
    {
        init();
        intListTester.addLast(1);
        assertEquals(1, intListTester.getFirst());
        intListTester.addFirst(2);
        assertEquals(2, intListTester.getFirst());
    }
}
