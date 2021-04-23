/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mario
 */
public class DamageTest {
    
    private static Weapon mock = new Weapon("", WeaponType.LASER, 0);
    private static Weapon mockPlasma = new Weapon("", WeaponType.PLASMA, 0);
    private static ShieldBooster mocks = new ShieldBooster("", 1,1);
    
    public DamageTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of adjust method, of class Damage.
     */
    @org.junit.jupiter.api.Test
    public void testAdjust() {
        System.out.println("adjust");
        
        
        ArrayList<Weapon> w = (new ArrayList<>());
        w.add(mock);
        w.add(mock);
        
        ArrayList<ShieldBooster> s = new ArrayList<>();
        s.add(mocks);
        
        Damage instance = new Damage(3, 3);
        Damage expResult = new Damage(1, 2);
        
        
        Damage result = instance.adjust(w, s);
        assertEquals(expResult.getNWeapons(), result.getNWeapons());
        assertEquals(expResult.getNShields(), result.getNShields());
        assertEquals(expResult.getWeapons(), result.getWeapons());
    }

    /**
     * Test of discardWeapon method, of class Damage.
     */
    @org.junit.jupiter.api.Test
    public void testDiscardWeapon() {
        System.out.println("discardWeapon");
        
        Weapon w = new Weapon(mock);
        Damage instance = new Damage(0, 0);
        instance.discardWeapon(w);
        
        assertEquals(instance.getNWeapons(), 0);
        
        instance = new Damage(0, 1);
        instance.discardWeapon(w);
        assertEquals(instance.getNWeapons(), 0);
        
        ArrayList<WeaponType> wl =new ArrayList<WeaponType>();
        wl.add(WeaponType.PLASMA);
        instance = new Damage(wl, 0);
        instance.discardWeapon(w);
        assertEquals(instance.getWeapons().size(), 1);
        
    }

    /**
     * Test of hasNoEffect method, of class Damage.
     */
    @org.junit.jupiter.api.Test
    public void testHasNoEffect() {
        System.out.println("hasNoEffect");
        Damage instance = new Damage(0,0);
        boolean expResult = true;
        boolean result = instance.hasNoEffect();
        assertEquals(expResult, result);
        
        instance = new Damage(1,1);
        assertEquals(instance.hasNoEffect(), false);
        
        instance = new Damage(0,1);
        assertEquals(instance.hasNoEffect(), false);
        
        instance = new Damage(1,0);
        assertEquals(instance.hasNoEffect(), false);
    }

    
}
