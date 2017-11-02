package e.cafe;

import java.util.ArrayList;
import java.util.Date;

//delivery order class
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