/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import static java.lang.System.exit;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
    
        ArrayList<item> cafeItems = new ArrayList<>();
        ArrayList<order> cafeOrders = new ArrayList<>();
        Scanner inp = new Scanner(System.in);
        
        //making database connection
        try{  
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost/ecafe","uzair","seecs");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from item");  
            while(rs.next()){
                //items from database
                cafeItems.add(new item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        
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
            Date myDate=new Date();
        
            while(true){
                System.out.println("Do you want to place a pickup order or delivery? (P or D)");
                System.out.println("Q to get the last month summary (any other to exit)");
                orderType=inp.next();
        
                if(orderType.equals("P")){
                    myPickOrder=new pickup_order();
                    System.out.println("Enter time for you order (hours and mins seperated by a space)");
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
                else if(orderType.equals("Q")){
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%s %50s%n", " ", "Monthly Report"));
                    sb.append(String.format("%s %48s%n", " ", "E - Cafe"));
                    sb.append(String.format("%s %n", " "));
                                
                    Statement sum_stmt = con.createStatement();  
                    ResultSet order_summary = sum_stmt.executeQuery("select * from order_list");  
                    while(order_summary.next()){
                        Statement user_stmt = con.createStatement();  
                        ResultSet user_name = user_stmt.executeQuery("select * from users where user_id ="+order_summary.getInt(2));  
                        user_name.next();
                        sb.append(String.format("%s %-20s %-20s%n%n", " ", "Order By : ",user_name.getString(2)));
                        sb.append(String.format("%s %-20s %-20s%n", " ", "Time : ",order_summary.getString(5)));
                        sb.append(String.format("%s %-20s%n%n", " ", "-------------------------------------------"));
                        sb.append(String.format("%s %-20s %-20s%n", " ", "Items", "Price"));
                        sb.append(String.format("%s %-20s %-20s%n", " ", "--------------", "--------------"));
                                
                        int order_id = order_summary.getInt(1);
                        Statement order_stmt = con.createStatement();  
                        ResultSet items_id = order_stmt.executeQuery("select item_id from order_items where order_id="+ order_id);        
                        while(items_id.next()){
                            int id = items_id.getInt(1);
                            Statement item_stmt = con.createStatement();  
                            ResultSet items = item_stmt.executeQuery("select * from item where item_id="+ id);        
                            while(items.next()){
                                sb.append(String.format("%s %-20s %-20s%n", " ", items.getString(2) , items.getInt(4)));
                            }
                        }
                        sb.append(String.format("%s %-20s %-20s%n", " ", "==============", "=============="));
                        sb.append(String.format("%s %-20s %-20s%n%n", " ", "Total", order_summary.getInt(4)));
                        sb.append(String.format("%s %-20s %-20s%n%n", " ", "==============", "=============="));
                        
                    }
                    
                    Writer Candidateoutput = null;
                    File Candidatefile = new File("summary.txt");
                    Candidateoutput = new BufferedWriter(new FileWriter(Candidatefile));
                    Candidateoutput.write(sb.toString());
                    Candidateoutput.close();
                    System.out.println(sb.toString());
                    
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
                
                if(orderType.equals("P")){
                    String Order_Query = "INSERT INTO `order_list`(`order_id`, `user_id`, `order_type`, `order_bill`, `order_time`) VALUES (NULL,?,?,?,?)";
                    PreparedStatement order_stmt = con.prepareStatement(Order_Query,Statement.RETURN_GENERATED_KEYS);
                    order_stmt.setInt(1, 2);
                    order_stmt.setString(2, orderType);
                    order_stmt.setInt(3, myPickOrder.get_order_bill());
                    order_stmt.setDate(4, new java.sql.Date(myDate.getDate()));
                    
                    int affectedRows = order_stmt.executeUpdate();
                    ResultSet keys = order_stmt.getGeneratedKeys();
                    keys.next();
                    int order_id = keys.getInt(1);
                
                    List<item> items = myPickOrder.order_items;
                    for(int i=0;i<items.size();i++){
                        String Order_Item_Query = "INSERT INTO `order_items`(`order_id`, `item_id`) VALUES ("+order_id+","+items.get(i).get_item_id()+")";
                        Statement item_stmt=con.createStatement();  
                        boolean res = item_stmt.execute(Order_Item_Query); 
                    
                    }
                    myCafe.add_order(myPickOrder);
                }
                else if(orderType.equals("D")){
                    String Order_Query = "INSERT INTO `order_list`(`order_id`, `user_id`, `order_type`, `order_bill`, `order_time`) VALUES (NULL,?,?,?,?)";
                    PreparedStatement order_stmt = con.prepareStatement(Order_Query,Statement.RETURN_GENERATED_KEYS);
                    order_stmt.setInt(1, 2);
                    order_stmt.setString(2, orderType);
                    order_stmt.setInt(3, myDelOrder.get_order_bill());
                    order_stmt.setDate(4, new java.sql.Date(myDate.getDate()));
                    
                    int affectedRows = order_stmt.executeUpdate();
                    ResultSet keys = order_stmt.getGeneratedKeys();
                    keys.next();
                    int order_id = keys.getInt(1);
                
                    List<item> items = myDelOrder.order_items;
                    for(int i=0;i<items.size();i++){
                        String Order_Item_Query = "INSERT INTO `order_items`(`order_id`, `item_id`) VALUES ("+order_id+","+items.get(i).get_item_id()+")";
                        Statement item_stmt=con.createStatement();  
                        boolean res = item_stmt.execute(Order_Item_Query); 
                    
                    }
                    myCafe.add_order(myDelOrder);
                }
            
            
                con.close();  
            }
        }
        catch(Exception e){ 
            System.out.println(e);
        }
    }
}   

    

