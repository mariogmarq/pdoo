/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author inmagalvez
 */
public class NumericDamage extends Damage{
    
    private int nWeapons;
    
    NumericDamage(int w, int s){
        super(s);
        nWeapons = w;
    }
    
    NumericDamage(NumericDamage other){
        this(other.nWeapons, other.getNShields());
    }
    
    @Override
    Damage copy(){
        return new NumericDamage(this);
    }
    
    @Override
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int shields = adjustShields(s);
        return new NumericDamage(Integer.min(nWeapons, w.size()), shields);
    }
    
    @Override
    public void discardWeapon(Weapon w) {
        if(nWeapons > 0){
            nWeapons--;
        }
    }
    
    @Override
    public boolean hasNoEffect() {
        return (nWeapons == 0 && super.hasNoEffect());
    }
    
    public int getNWeapons() {
        return nWeapons;
    }
    
    
    
    @Override
    public String toString() {
        return super.toString() + "\nnWeapons: " + getNWeapons();
    }
    
    @Override
    DamageToUI getUIversion() {
        return new NumericDamageToUI(this);
    }
     
    
    
}
