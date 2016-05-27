/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.util.*;
import java.awt.*;

/**
 *
 * @author Zaylin
 */
public class ShoppingCart {
	
	private double total;
	private int size;
	private ArrayList<Item> ShoppingCart = new ArrayList<>();
	
	
	ShoppingCart(){
		total = 0.0;
		size = 0;
		
		
	}
	
    /**
     *
     * @param i
     */
    public void addToCart(Item i){
		ShoppingCart.add(i);
	}
	int searchItem(Item e)
	{
		for(int i = 0; i < ShoppingCart.size(); i++){
			if(e.getID() == ShoppingCart.get(i).getID()){
				return i;
			}
		}
		return -1;
	}
	
    /**
     *
     * @param e
     */
    public void removeItem(Item e){
		int search = searchItem(e);
		
		if(search != -1)
		{
			ShoppingCart.remove(search);
		}
	}
	//only edits quantity

    /**
     *
     * @param e
     * @param q
     */
    	public void editItem(Item e, int q){
		int search = searchItem(e);
		
		if(search != -1){
			ShoppingCart.get(search).setQuantity(q);
		}
		
	}
	
    /**
     *
     */
    public void calculateTotal(){
		
		for(int i = 0; i < ShoppingCart.size(); i++){
			total += ShoppingCart.get(i).getSP();
                }	
	}

    /**
     *
     * @param i
     * @return
     */
    public Item getItem(int i)
        {
            return ShoppingCart.get(i);
        }

    /**
     *
     * @return
     */
    public int calculateSize(){
            size = ShoppingCart.size();
		return ShoppingCart.size();
	}

}
