/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.HashSlingSlasher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author greatwmc
 */
public class NewEmptyJUnitTest2 {
    
    public NewEmptyJUnitTest2() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void fileHashTest() 
    {
        System.out.println(HashSlingSlasher.getUniqueFileName("notesABC.txt"));
        assert(null != HashSlingSlasher.getUniqueFileName("notesABC.txt"));
    }
}
