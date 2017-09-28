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
public class pickup_order extends order {
    private Date order_time;

    public pickup_order(){
        super();
        order_time=null;
    }
    
    public pickup_order(ArrayList<item> items, Date time){
        super(items);
        order_time=time;
    }
    
    public void set_time(Date time){
        order_time=time;
    }
    
    public void set_order_list(ArrayList<item> items){
        order_items=items;
    }
    
}
