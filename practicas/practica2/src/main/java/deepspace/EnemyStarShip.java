/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 */
public class EnemyStarShip {
    private String name;
    private float ammoPower;
    private float shieldPower;
    private Loot loot;
    private Damage damage;

    EnemyStarShip(String name, float ammoPower, float shieldPower, Loot loot, Damage damage) {
        this.name = name;
        this.ammoPower = ammoPower;
        this.shieldPower = shieldPower;
        this.loot = loot;
        this.damage = damage;
    }

    EnemyStarShip(EnemyStarShip e) {
        //Por hacer
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public Damage getDamage() {
        return new Damage(damage);
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public Loot getLoot() {
        return new Loot(loot.getNSupplies(), loot.getNWeapons(), loot.getNShields(), loot.getNHangars(), loot.getNMedals());
    }
    
    
    
}
