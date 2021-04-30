/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author inmagalvez
 */
public class SuppliesPackageTest {
    
    public SuppliesPackageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAmmoPower method, of class SuppliesPackage.
     */
    @Test
    public void testGetAmmoPower() {
        System.out.println("getAmmoPower");
        SuppliesPackage instance = new SuppliesPackage(2.5f, 4.9f, 3.0f);
        float expResult = 2.5F;
        float result = instance.getAmmoPower();
        assertEquals(expResult, result);
        
        
    }
    
}
