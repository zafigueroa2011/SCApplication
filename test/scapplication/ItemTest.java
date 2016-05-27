/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scapplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zaylin
 */
public class ItemTest {
    
    public ItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Item.
     */
    @Test
    public void testGetName() {
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        System.out.println("getName");
        String expResult = "ball";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategory method, of class Item.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        String expResult = "toys";
        String result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDetails method, of class Item.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        String expResult = "Perfect for the summer";
        String result = instance.getDetails();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Item.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        int expResult = 123;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class Item.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        int expResult = 20;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIP method, of class Item.
     */
    @Test
    public void testGetIP() {
        System.out.println("getIP");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        double expResult = 60.00;
        double result = instance.getIP();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSP method, of class Item.
     */
    @Test
    public void testGetSP() {
        System.out.println("getSP");
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        double expResult = 3.0;
        double result = instance.getSP();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setName method, of class Item.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String n = "basketballs";
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setName(n);
        System.out.println(instance.getName());
    }

    /**
     * Test of setCategory method, of class Item.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String c = "outdoor toys";
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setCategory(c);
        System.out.println(instance.getCategory());
    }

    /**
     * Test of setDetails method, of class Item.
     */
    @Test
    public void testSetDetails() {
        System.out.println("setDetails");
        String d = "Best for playing with your friends";
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setDetails(d);
        System.out.println(instance.getDetails());
    }

    /**
     * Test of setQuantity method, of class Item.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int q = 20;
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setQuantity(q);
        System.out.println(instance.getQuantity());
    }

    /**
     * Test of setIP method, of class Item.
     */
    @Test
    public void testSetIP() {
        System.out.println("setIP");
        double inv = 55.0;
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setIP(inv);
        System.out.println(instance.getIP());
    }

    /**
     * Test of setSP method, of class Item.
     */
    @Test
    public void testSetSP() {
        System.out.println("setSP");
        double sell = 5.0;
        Item instance = new Item("ball","toys","Perfect for the summer",123,20,60.00,3.00);
        instance.setSP(sell);
        System.out.println(instance.getSP());
    }
    
}
