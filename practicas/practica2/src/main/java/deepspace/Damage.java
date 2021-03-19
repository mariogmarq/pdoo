/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 * Representa el daño producido a una estación espacial por una naveenemiga cuando se pierde un combate
 */
public class Damage {
    private int nShields;
    private int nWeapons;
    private ArrayList<WeaponType> weapons;

    Damage(int s, int w) {
        this.nShields = s;
        this.nWeapons = w;
        weapons = new ArrayList<>();
    }
    
    Damage(ArrayList<WeaponType> wl, int s) {
        this.weapons = new ArrayList<>(wl);
        this.nShields = s;
        this.nWeapons = weapons.size();
    }
    
    Damage(Damage d) {
        this(d.weapons, d.nShields);
    }
    
    DamageToUI getUIversion() {
        return new DamageToUI(this);
    }
    
    /**
     * @param w array de armas
     * @param t tipo de arma
     * @return devuelve el indice del primer elemento de w que sea de tipo t, -1 si no hay ninguno
     */
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
    
    /**
     * Devuelve una versión ajustada del objeto a las colecciones de armas y potenciadores de escudos suministradas como parámetro.
     * @param w armas
     * @param s potenciadores
     * @return version ajustada del objeto
     * @deprecated no implementado
     */
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * intenta eliminar el tipo del arma pasada como parámetro de esa lista.
     * En otro caso simplementedecrementa en una unidad el contador de armas que deben ser eliminadas.
     * Ese contador no puedeser inferior a cero en ningún caso
     * @param w el arma
     */
    public void discardWeapon(Weapon w) {
        int index = weapons.indexOf(w.getType());
        if(index == -1) {
            nWeapons--;
            if(nWeapons < 0) {
                nWeapons = 0;
            }
        } else {
            weapons.remove(index);
        }
    }
    
    /**
     * Descarte un shieldBooster
     */
    public void discardShieldBooster() {
        nShields--;
        if(nShields < 0){
            nShields = 0;
        }
    }
    
    /**
     * @return rue si el daño representado no tiene ningún efecto.
     * Esto quiere decir que no implica la pérdida de ningún tipo de accesorio (armas o potenciadores de escudo).
     */
    public boolean hasNoEffect() {
        return (nWeapons == 0 && nShields == 0);
    }

    public ArrayList<WeaponType> getWeapons() {
        return new ArrayList<>(weapons);
    }

    public int getNWeapons() {
        return nWeapons;
    }

    public int getNShields() {
        return nShields;
    }

    @Override
    public String toString() {
        return "nShields: " + getNShields()+ "\nnWeapons: " + getNWeapons() + "\nWeapons: " + getWeapons().toString();
    }
    
    
    
}
