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
public class UserTest {
    
    public UserTest() {
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
     * Test of User method, of class User.
     */
    @Test
    public void testUser() {
        System.out.println("constructor");
        User user1 = new User("test","123");        
        System.out.println(user1);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User user1 = new User("test","123");
        String expResult = "123";
        String result = user1.getPassword();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User user1 = new User("test","123");
        String expResult = "test";
        String result = user1.getUsername();
        System.out.println(result);
        assertEquals(expResult, result);
        
    }
    
}
