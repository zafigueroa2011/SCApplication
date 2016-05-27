/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

/**
 *
 * @author Zaylin
 */
public class Buyer extends User{
	
	Buyer(String u, String p)
	{
            super(u,p);
            storage = new Storage();
            sc = new ShoppingCart();
	}
	
	//@param sc stores all of the items the buyer wishes to purchase

    /**
     *
     */
    	public ShoppingCart sc;

    /**
     *
     */
    public Storage storage;
}
