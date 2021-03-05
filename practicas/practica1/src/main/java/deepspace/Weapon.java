/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * representa a las armas de las que puede disponer una estación espacial para potenciar suenergía al disparar.
 */
public class Weapon {
    private String name;
    private WeaponType type;
    private int uses;

    Weapon(String name, WeaponType type, int uses) {
        this.name = name;
        this.type = type;
        this.uses = uses;
    }
    
    Weapon(Weapon w) {
        this.name = w.name;
        this.type = w.type;
        this.uses = w.uses;
    }
    
    public WeaponType getType(){
        return type;
    }
    
    public int getUses() {
        return uses;
    }
    
    public float power() {
        return type.getPower();
    }
    
    public float useIt(){
        if(uses > 0){
            uses--;
            return power();
        }
        
        return 1.0f;
    }
}
