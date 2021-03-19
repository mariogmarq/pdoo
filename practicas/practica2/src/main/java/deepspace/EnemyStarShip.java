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
        this(e.getName(), e.getAmmoPower(), e.getShieldPower(), e.getLoot(), e.getDamage());
    }
    
    public float fire() {
        return ammoPower;
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
    
    public float protection() {
        return shieldPower;
    }
    
    /**
     * @param shot potencia del disparo
     * @return el resultado del disparo recibido, si el nivel de la protección de los escudos es menor que la intensidad del disparo,
     * la nave enemiga no resiste (DONOTRESIST).  En casocontrario resiste el disparo (RESIST).
     * 
     */
    public ShotResult recieveShot(float shot) {
        if(shot > getShieldPower()){
            return ShotResult.DONOTRESIST;
        }
        
        return ShotResult.RESIST;
    }

    public Loot getLoot() {
        return new Loot(loot.getNSupplies(), loot.getNWeapons(), loot.getNShields(), loot.getNHangars(), loot.getNMedals());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nammoPower: " + ammoPower + "\nShieldPower: " + shieldPower + "\nLoot: " + loot.toString()
                + "\nDamage: " + damage.toString();
    }
    
    
    
}
