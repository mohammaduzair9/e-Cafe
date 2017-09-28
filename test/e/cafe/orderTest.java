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
public class orderTest {
    
    @Test
    public void testAdd_order_item() {
        System.out.println("add_order_item");
        item i = new item("Malai Boti","mainCourse",500) ;
        order instance = new order();
        instance.add_order_item(i);
        
        int expRes=500;
        int Result=instance.get_order_bill();
        
        assertEquals(expRes, Result);
    }

    @Test
    public void testCalc_order_bill() {
        System.out.println("calc_order_bill");
        ArrayList<item> myList = new ArrayList<>();
        myList.add(new item("Item","appetizer",300));
        myList.add(new item("Item","appetizer",500));
        myList.add(new item("Item","appetizer",200));
        order instance = new order(myList);
        
        int expResult = 1000;
        int result = instance.get_order_bill();
        assertEquals(expResult, result);

    }

 
}
