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
public class SpecificDamage extends Damage {
    
   private ArrayList<WeaponType> weapons;
   
   SpecificDamage(ArrayList<WeaponType> wl, int s) {
        super(s);
        weapons = new ArrayList<>(wl);
    }
   
   SpecificDamage(SpecificDamage other){
        this(other.weapons, other.getNShields());
    }
    
    @Override
    Damage copy(){
        return new SpecificDamage(this);
    }
    
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t) {
        int index = -1;
        for (int i = 0; i < w.size(); i++) {
            if(w.get(i).getType() == t) {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    @Override
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int shields = adjustShields(s);

        ArrayList<Weapon> wAux = new ArrayList<>(w);
        ArrayList<WeaponType> toSet = new ArrayList<>();

        for(WeaponType element: weapons) {
           int index = arrayContainsType(wAux, element);

            if(index != -1){  //If the element is found
                toSet.add(element); //It can be removed
                wAux.remove(index);
            }
        }

        return new SpecificDamage(toSet, shields);
    }
    
    @Override
    public void discardWeapon(Weapon w) {
        
        int index = weapons.indexOf(w.getType());
        if(index != -1){
            weapons.remove(index);
        }
        
    }
   
    
    @Override
    public boolean hasNoEffect() {
        return (weapons.isEmpty() && super.hasNoEffect());
    }
    
    public ArrayList<WeaponType> getWeapons() {
        if(weapons==null){
            return null;
        }
        return new ArrayList<>(weapons);
    }
    
    
    @Override
    public String toString() {
        return super.toString() + "\nweapons: " + getWeapons();
    }
    
    @Override
    DamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }
    
    
}
