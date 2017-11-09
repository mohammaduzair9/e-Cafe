package com.ecafe.maven_ecafe;

//item class
public class item {
    
    private int item_id;
    private String item_name;
    private String item_type;
    private int item_price;
    
    public item(){
        item_id=0;
        item_name=null;
        item_type=null;
        item_price=0;
    }
    
    public item(int id,String name, String type, int price){
        item_id=id;
        item_name=name;
        item_type=type;
        item_price=price;
    }
    
    public int get_item_id(){
        return item_id;
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
