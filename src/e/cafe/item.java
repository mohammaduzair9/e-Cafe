/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

/**
 *
 * @author UZAIR
 */
public class item {
    
    private String item_name;
    private String item_type;
    private int item_price;
    
    public item(){
        item_name=null;
        item_type=null;
        item_price=0;
    }
    
    public item(String name, String type, int price){
        item_name=name;
        item_type=type;
        item_price=price;
    }
    
    public void set_item_name(String name){
        item_name=name;
    }
    
    public void set_item_type(String type){
        item_type=type;
    }
    
    public void set_item_price(int price){
        item_price=price;
    }
    
    public String get_item_name(){
        return item_name;
    }
    
    public String get_item_type(){
        return item_type;
    }
    
    public int get_item_price(){
        return item_price;
    }
            
    
}
