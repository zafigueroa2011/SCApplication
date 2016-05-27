/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.util.ArrayList;

/**
 *Class that creates an Inventory or a list of all the items for sale
 * by a specific seller.
 * @author Zaylin
 */
public class Inventory 
{
    /**
     * Constructs a inventory object.
     */
    Inventory()
    {
        inventory = new ArrayList <>();
    }
    /**
     * Adds one item to the inventory.
     * @param i the product to add to the inventory.
     * @postcondition inventory size > 0
     */
    void addItem(Item i)
    {
        inventory.add(i);
    }
    /**
     * Search if an item is in the inventory and returns the place of the item.
     * If it is not found then a negative number is returned.
     * @param e the item to find in the inventory
     * @return the place of the item
     */
    int searchItem(Item e)
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            if(e.getID() == inventory.get(i).getID())
            {
                return i;
            }
        }
        return -1;
    }
    /**
     * Edits the name of the item.
     * @param e the item to edit
     * @param n the new name for the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemName(Item e, String n)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setName(n);
        }
    }
    /**
     * Edits the category of the item
     * @param e the item to edit
     * @param c the new category for the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemCategory(Item e, String c)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setCategory(c);
        }
    }
    /**
     * Edits the details of the item
     * @param e the item to edit
     * @param d the new details of the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemDetails(Item e, String d)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setDetails(d);
        }
    }
    /**
     * Edits the quantity of the item
     * @param e the item to edit
     * @param q the new quantity for the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemQnt(Item e, int q)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setQuantity(q);
        }
    }
    /**
     * Edits the selling price of the item
     * @param e the item to edit
     * @param s the new selling price for the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemSP(Item e, double s)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setSP(s);
        }
    }
    /**
     * Edits the invoice price of the item
     * @param e the item to edit
     * @param i the new invoice price for the item
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void editItemIP(Item e, double i)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            inventory.get(search).setIP(i);
        }
    }
    /**
     * Removes an item from the inventory.
     * @param e the item to edit
     * @precondition e must be in the inventory, inventory.size > 0
     */
    void removeItem(Item e)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
           inventory.remove(search);
        }
    }
    /**
     * Returns the size of the inventory
     * @return size of the inventory
     */
    int size()
    {
        return inventory.size();
    }
    /**
     * Returns an Item at index i.
     * @param i place of the item in the inventory.
     * @return an item object.
     */
    Item getItem(int i)
    {
        return inventory.get(i);
    }
    /**
     * @param inventory stores the items for sell
     */
    ArrayList<Item> inventory;
}
