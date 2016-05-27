/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.io.Serializable;

/**
 *Class to create a user object with a username and password
 * @author Zaylin
 */
public class User implements Serializable
{
    /**
     * Constructor for a User object.
     * @param u the username of the user
     * @param p the password of the user
     */
    User(String u, String p)
    {
        username = u;
        password = p;
    }
    /**
     * Gets the password of the user.
     * @return the password of the user
     */
    String getPassword()
    {
        return password;
    }
    /**
     * Gets the username of the user.
     * @return the username of the user
     */
    String getUsername()
    {
        return username;
    }
    /**
     * Converts a user into a string.
     * @return string format of a user
     */
    public String toString()
    {
        String result = getUsername()+ "-" +getPassword();
        return result;
    }
    /**
     * @param username stores the username of the user
     * @param password stores the password of the user
     */
    private String username;
    private String password;
}
