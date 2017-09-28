/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author UZAIR
 */
public class delivery_order extends order {
    private String order_address;

    public delivery_order(){
        super();
        order_address=null;
    }
    
    public delivery_order(ArrayList<item> items, String address){
        super(items);
        order_address=address;
    }
    
    public void set_address(String add){
        order_address=add;
    }
    
}