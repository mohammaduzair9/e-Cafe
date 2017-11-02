package e.cafe;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class menuTest {
    
    public menuTest() {
    }
    
    @Test
    public void testGet_menu_items() {
        System.out.println("get_menu_items");
        item newItem = new item(1,"item","type",100);
        ArrayList<item> explist = new ArrayList<>();
        explist.add(newItem);
        menu instance = new menu(explist,"menu");
        ArrayList<item> expResult = new ArrayList<>();
        expResult.add(new item(1,"item","type",100));
        ArrayList<item> result = instance.get_menu_items();
        assertEquals(expResult.get(0), expResult.get(0));
        
    }

    
}
