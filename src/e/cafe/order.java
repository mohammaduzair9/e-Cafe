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
public class order {
    public ArrayList<item> order_items;
    private int order_bill;
    
    public order(){
        order_items=new ArrayList<>();
        order_bill=0;
    }
    
    public order(ArrayList<item> items){
        order_items=items;
        order_bill=calc_order_bill();
    }
    
    public void add_order_item(item i){
        order_items.add(i);
        calc_order_bill();
    }
    
    public int calc_order_bill(){
        int bill=0;
        for(int i=0;i<order_items.size();i++){
            bill+=order_items.get(i).get_item_price();
        }
        order_bill=bill;
        return bill;
    }
    
    public int get_order_bill(){
        return order_bill;
    }
    
}
