package com.ecafe.maven_ecafe;

import org.junit.*;
import static org.junit.Assert.*;

public class itemTest {
    
    @Test
    public void testGet_item_name() {
        System.out.println("get_item_name");
        item instance = new item(1,"Item","Type",200);
        String result=instance.get_item_name();
        String expResult="Item";
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGet_item_type() {
        System.out.println("get_item_type");
        item instance = new item(1,"Item","Type",200);
        String result=instance.get_item_type();
        String expResult="Type";
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGet_item_price() {
        System.out.println("get_item_price");
        item instance = new item(1,"Item","Type",200);
        int result=instance.get_item_price();
        int expResult=200;
        assertEquals(expResult, result);
    }
    
}
