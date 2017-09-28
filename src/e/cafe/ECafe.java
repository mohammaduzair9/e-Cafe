/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ECafe {

    private static menu cafe_menu;
    private ArrayList<order> cafe_orders;
    private Date cafe_open_time;
    private Date cafe_close_time;
    private int cooks;
    
    public ECafe(){
        cafe_menu=null;
        cafe_orders=null;
        cafe_open_time=null;
        cafe_close_time=null;
        cooks=0;
    }

    public ECafe(menu cafemenu, ArrayList<order> cafeorders,Date open,Date close, int cafecooks){
        cafe_menu=cafemenu;
        cafe_orders=cafeorders;
        cafe_open_time=open;
        cafe_close_time=close;
        cooks=cafecooks;
    }
    
    static public menu get_menu(){
        return cafe_menu;
    }

    public int get_open(){
        return cafe_open_time.getHours();
    }
    
    public int get_close(){
        return cafe_close_time.getHours();
    }
    
    public void add_order(order newOrder ){
        cafe_orders.add(newOrder);
    } 
    
    public void displayMenu(){
        int item_num=0;
        ArrayList<item> appetizerList=this.cafe_menu.get_appetizers();
        ArrayList<item> soupList=this.cafe_menu.get_soups();
        ArrayList<item> mainCourseList=this.cafe_menu.get_main_course_dishes();
        ArrayList<item> sideDishList=this.cafe_menu.get_side_dishes();
        
        System.out.println("------WELCOME TO E-CAFE---------");
        
        //displaying appetizers
        System.out.println("--APPETIZERS--");
        for(int i=0;i<appetizerList.size();i++){
            item_num++;
            System.out.printf("%-10d %-20s %-10d",item_num,appetizerList.get(i).get_item_name(),appetizerList.get(i).get_item_price());
            System.out.println("");
        }
        
        System.out.println("--SOUPS--");
        for(int i=0;i<soupList.size();i++){
            item_num++;
            System.out.printf("%-10d %-20s %-10d",item_num,soupList.get(i).get_item_name(),soupList.get(i).get_item_price());
            System.out.println("");
        }
        
        System.out.println("--MAIN COURSE DISHES--");
        for(int i=0;i<mainCourseList.size();i++){
            item_num++;
            System.out.printf("%-10d %-20s %-10d",item_num,mainCourseList.get(i).get_item_name(),mainCourseList.get(i).get_item_price());
            System.out.println("");
            
        }
        
        System.out.println("--SIDE DISHES--");
        for(int i=0;i<sideDishList.size();i++){
            item_num++;
            System.out.printf("%-10d %-20s %-10d",item_num,sideDishList.get(i).get_item_name(),sideDishList.get(i).get_item_price());
            System.out.println("");        
        }
        
    }
    
    public boolean PickUptimeCheck(Date orderTime) {
        if(orderTime.getHours()>= cafe_open_time.getHours() && orderTime.getHours()<cafe_close_time.getHours()){
            return true;
        }
        else return false;
        
        
    }
    
    public static void main(String[] args) {
    
        Scanner inp = new Scanner(System.in);
        
        ArrayList<item> cafeItems = new ArrayList<>();
        cafeItems.add(new item("Dips and Chicks","appetizer",300));
        cafeItems.add(new item("Chicken Fries","appetizer",350));
        cafeItems.add(new item("Hommus","appetizer",500));
        cafeItems.add(new item("Chicken Wings","appetizer",600));
        
        cafeItems.add(new item("Egg Drop Soup","soup",450));
        cafeItems.add(new item("Vegetable Soup","soup",650));
        cafeItems.add(new item("Chicken Handi","mainCourse",1050));
        cafeItems.add(new item("Chicken Karahi","mainCourse",850));
        cafeItems.add(new item("Moroccon Steak","mainCourse",1150));
        cafeItems.add(new item("Chicken Burger","mainCourse",550));
        cafeItems.add(new item("Malai Boti","mainCourse",950));
        cafeItems.add(new item("Fried Fish","mainCourse",750));
        cafeItems.add(new item("Salad","sideDish",200));
        cafeItems.add(new item("Raita","sideDish",100));
        cafeItems.add(new item("Bacon","sideDish",150));
        
        ArrayList<order> cafeOrders = new ArrayList<>();
        
        menu cafeMenu = new menu(cafeItems, "General");
        
        Date openTime = new Date();
        openTime.setHours(11);
        openTime.setMinutes(0);
        
        Date closeTime = new Date();
        closeTime.setHours(22);
        closeTime.setMinutes(0);
        
        ECafe myCafe = new ECafe(cafeMenu, cafeOrders, openTime, closeTime, 4);
        myCafe.displayMenu();
        
        String orderType = null;
        pickup_order myPickOrder = null;
        delivery_order myDelOrder = null;
        
        while(true){
            System.out.println("Do you want to place a pickup order or delivery? (P or D) (any other to exit)");
            orderType=inp.next();
        
            if(orderType.equals("P")){
                myPickOrder=new pickup_order();
                System.out.println("Enter time for you order (hours and mins seperated by a space)");
                Date myDate=new Date();
                myDate.setHours(inp.nextInt());
                myDate.setMinutes(inp.nextInt());
                if(myCafe.PickUptimeCheck(myDate)){
                    myPickOrder.set_time(myDate);
                }
                else{
                    System.out.println("The Cafe is open between 11 AM and 10 PM");
                    continue;
                }
            }
            
            else if(orderType.equals("D")){
                Date myDate = new Date();
                
                if(myDate.getHours()< myCafe.get_open() && myDate.getHours()>=myCafe.get_close()){
                    System.out.println("Shop is closed now, cafe Timings are 11 AM to 10 PM");
                    continue;
                }
                else{
                    myDelOrder=new delivery_order();
                    System.out.println("Enter your address");
                    myDelOrder.set_address(inp.next()); 
                }
            }
            else{
                System.out.println("Thankyou for visiting us");
                break;
            }
        
            int opt;
            while(true){
                System.out.println("Enter item number to add to your order (-1 for placing order)");
                opt =  inp.nextInt();
            
                if(opt==-1){
                    break;
                }
                else{
                    if(orderType.equals("P")){
                        myPickOrder.add_order_item(myCafe.get_menu().get_menu_items().get(opt-1));
                        System.out.print("\n Your Bill :");
                        System.out.print(myPickOrder.get_order_bill()+"\n");
                    }
                    else if(orderType.equals("D")){
                        myDelOrder.add_order_item(myCafe.get_menu().get_menu_items().get(opt-1));
                        System.out.print("\n Your Bill :");
                        System.out.print(myDelOrder.get_order_bill()+"\n");
                    }
                }
            }
            
            if(orderType=="P"){
                myCafe.add_order(myPickOrder);
            }
            else if(orderType=="D"){
                myCafe.add_order(myDelOrder);
            }
        
        
        }
        
        
        
        
            
        
        
    }   

    
}
