/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author UZAIR
 */
public class menuTest {
    
    public menuTest() {
    }
    
    @Test
    public void testGet_menu_items() {
        System.out.println("get_menu_items");
        item newItem = new item("item","type",100);
        ArrayList<item> explist = new ArrayList<>();
        explist.add(newItem);
        menu instance = new menu(explist,"menu");
        ArrayList<item> expResult = new ArrayList<>();
        expResult.add(new item("item","type",100));
        ArrayList<item> result = instance.get_menu_items();
        assertEquals(expResult.get(0), expResult.get(0));
        
    }

    
}
