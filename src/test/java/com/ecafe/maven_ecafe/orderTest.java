package com.ecafe.maven_ecafe;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class orderTest {
    
    @Test
    public void testAdd_order_item() {
        System.out.println("add_order_item");
        item i = new item(1,"Malai Boti","mainCourse",500) ;
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
        myList.add(new item(1,"Item","appetizer",300));
        myList.add(new item(2,"Item","appetizer",500));
        myList.add(new item(3,"Item","appetizer",200));
        order instance = new order(myList);
        
        int expResult = 1000;
        int result = instance.get_order_bill();
        assertEquals(expResult, result);

    }

 
}
