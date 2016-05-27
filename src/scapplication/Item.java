/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.util.Date;

/**
 *Class that creates an Item object to describe a product for sale.
 * @author Zaylin
 */
class Item 
{
    /**
     * Constructs an Item object.
     * @param n name of the product
     * @param c category of the product
     * @param d details of the product
     * @param qnt the quantity of the item
     * @param invP the invoice price of the item
     * @param sellP the selling price of the item
     * @param s the username of the seller
     */
    Item(String n, String c, String d, int qnt, double invP, double sellP, String s)
    {
        name = n;
        ID = (int)new Date().getTime();
        category = c;
        details = d;
        quantity = qnt;
        invoicePrice = invP;
        sellingPrice = sellP;
        seller = s;
    }
    /**
     * Constructor for an Item object.(only when an item has already been created and has an ID)
     * @param n name of the product
     * @param c category of the product
     * @param d details of the product
     * @param id the ID of the item
     * @param qnt the quantity of the item
     * @param invP the invoice price of the item
     * @param sellP the selling price of the item
     * @param s the username of the seller
     */
    Item(String n, String c, String d, int id, int qnt, double invP, double sellP, String s)
    {
        name = n;
        ID = id;
        category = c;
        details = d;
        quantity = qnt;
        invoicePrice = invP;
        sellingPrice = sellP;
        seller = s;
    }
    /**
     * Gets the name of the item.
     * @return a string with the name of the item
     */
    String getName()  
    {  
        return name; 
    }
    /**
     * Gets the category of the item.
     * @return a string with the category of the item
     */
    String getCategory()
    {
        return category;
    }
    /**
     * Gets the details of the item.
     * @return a string with the details of the item
     */
    String getDetails()
    {
        return details;
    }
    String getSeller()
    {
        return seller;
    }
    /**
     * Gets the ID of the item.
     * @return the ID of the item
     */
    int getID()
    {
        return ID;
    }
    /**
     * Gets the quantity of the item.
     * @return the quantity of the item
     */
    int getQuantity()
    {
        return quantity;
    }
    /**
     * Gets the invoice price of the item.
     * @return invoice price of the item
     */
    double getIP()
    {
        return invoicePrice;
    }
    /**
     * Gets the selling price of the item.
     * @return the selling price of the item
     */
    double getSP()
    {
        return sellingPrice;
    }
    /**
     * Changes the name of the item.
     * @param n the new name of the item
     */
    void setName(String n)  
    {  
        name = n;
    }
    /**
     * Changes the category of the item.
     * @param c the new category of the item
     */
    void setCategory(String c)
    {
        category = c;
    }
    /**
     * Changes the details of the item.
     * @param d the new details of the item
     */
    void setDetails(String d)
    {
        details = d;
    }
    /**
     * Changes the quantity of the item.
     * @param q 
     */
    void setQuantity(int q)
    {
       quantity = q;
    }
    /**
     * Changes the invoice price of the item.
     * @param inv the new invoice price of the item
     */
    void setIP(double inv)
    {
        invoicePrice = inv;
    }
    /**
     * Changes the selling price of the item.
     * @param sell the new selling price of the item
     */
    void setSP(double sell)
    {
        sellingPrice = sell;
    }
    /**
     * Convert an item to a string.
     * @return the string format of the item
     */
    public String toString()
    {
        String result = ID + "-" + name + "-" + quantity + "-" + sellingPrice + "-" + invoicePrice + "-" + category + "-" + details+ "-" + seller;
        return result;
    }
    /**
     * @param name stores the name of the item
     * @param category stores the category of the item
     * @param details stores the details of the item
     * @param ID stores the ID of the item
     * @param quantity stores the quantity of the item
     * @param invoicePrice stores the invoicePrice  of the item
     * @param sellingPrice stores the sellingPrice of the item
     * @param seller the username of the seller of the item
     */
    private String name;
    private String category;
    private String details;
    private int ID;
    private int quantity;
    private double invoicePrice;
    private double sellingPrice;
    private String seller;
}
