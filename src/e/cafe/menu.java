/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.cafe;

import java.util.ArrayList;

/**
 *
 * @author UZAIR
 */
public class menu {
    private ArrayList<item> menu_items;
    private String menu_name;
    
    public menu(){
        menu_items=null;
        menu_name=null;
    }
    
    public menu(ArrayList<item> items,String name){
        menu_items=items;
        menu_name=name;
    }
    
    public void add_item(item new_item){
        menu_items.add(new_item);
    }
    
    public ArrayList<item> get_menu_items(){
        return menu_items;
    }
    
    public String get_menu_name(){
        return menu_name;
    }
    
    public ArrayList<item> get_appetizers(){
        
        ArrayList<item> appetizers=new ArrayList<>();
        for(int i=0;i<menu_items.size();i++){
            if(menu_items.get(i).get_item_type()=="appetizer"){
                appetizers.add(menu_items.get(i));
            }
        }
        return appetizers; 
    }
    
    public ArrayList<item> get_soups(){
        
        ArrayList<item> soups=new ArrayList<>();
        for(int i=0;i<menu_items.size();i++){
            if(menu_items.get(i).get_item_type()=="soup"){
                soups.add(menu_items.get(i));
            }
        }
        return soups; 
    }
    
    public ArrayList<item> get_main_course_dishes(){
        
        ArrayList<item> main_course_dishes=new ArrayList<>();
        for(int i=0;i<menu_items.size();i++){
            if(menu_items.get(i).get_item_type()=="mainCourse"){
                main_course_dishes.add(menu_items.get(i));
            }
        }
        return main_course_dishes; 
    }
    
    public ArrayList<item> get_side_dishes(){
        
        ArrayList<item> side_dishes=new ArrayList<>();
        for(int i=0;i<menu_items.size();i++){
            if(menu_items.get(i).get_item_type()=="sideDish"){
                side_dishes.add(menu_items.get(i));
            }
        }
        return side_dishes; 
    }
    
    
}
