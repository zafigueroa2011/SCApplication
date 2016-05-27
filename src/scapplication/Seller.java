/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

/**
 *Class that describes a seller object which inherits from user
 * and has an inventory.
 * @author Zaylin
 */
public class Seller extends User
{
    /**
     * Constructor for a seller object
     * @param u the username of the seller
     * @param p the password of the seller
     */
    Seller(String u, String p)
    {
        super(u,p);
        profits = 0;
        costs = 0;
        revenues = 0;
    }
    /**
     * Sets the revenues of the seller if it has not been set.
     * @param rev the revenues of the seller
     */
    void setRevenues(double rev)
    {
        if(revenues == 0)
        {
            revenues = rev;
        }
    }
    /**
     * Sets and calculates the costs of the seller
     * @param c costs
     */
    void setCosts(double c)
    {
        if(costs == 0)
        {
            costs = c;
        }
        else
        {
            costs =costs + c;
        }
    }
    /**
     * Gets the costs of the seller.
     * @return costs
     */
    double getCosts()
    {
        return costs;
    }
    /**
     * Gets the revenues of the seller.
     * @return revenues
     */
    double getRevenues()
    {
        return revenues;
    }
    /**
     * Calculates the profits of the seller
     * @return the profits
     */
    double calculateProfits()
    {
        profits = revenues - costs;
        return profits;
    }
    /**
     * Converts the seller object to a string.
     * @return string format of a seller object
     */
    public String toString()
    {
        String result = this.getUsername() + "-" + revenues + "-" + costs;
        return result;
    }
    /**
     * @param profits stores the profits of the seller
     * @param inv stores all the items of the seller
     * @param costs stores how much the seller has spent
     * @param revenues stores how much money the seller has received.
     */
    private double profits;
    private double costs;
    private double revenues;

    /**
     *
     */
    public Inventory inv = new Inventory();
}
