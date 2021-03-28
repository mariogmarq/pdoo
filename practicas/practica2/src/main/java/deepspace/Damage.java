/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa el daño producido a una estación espacial por una naveenemiga cuando se pierde un combate
 */
public class Damage {
    private final static int NOUSED = -1;
    private int nShields;
    private int nWeapons;
    private ArrayList<WeaponType> weapons;

    Damage(int s, int w) {
        this.nShields = s;
        this.nWeapons = w;
        weapons = null;
    }
    
    Damage(ArrayList<WeaponType> wl, int s) {
        this.weapons = new ArrayList<>(wl);
        this.nShields = s;
        this.nWeapons = NOUSED;
    }
    
    Damage(Damage d) {
        this(d.getNWeapons(), d.getNShields());
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
     */
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int shields = Integer.min(s.size(), nShields);
        if(weapons == null){
            //Caso tenemos contadores
            return new Damage(Integer.min(nWeapons, w.size()), shields);
        }
        
        //Caso weapons es una lista, creamos una copia de weapons
        ArrayList<WeaponType> copy = new ArrayList<>(weapons);
        
        //Borramos los tipos que no estan en w
        int i = 0;
        while(i < copy.size()) {
            if(arrayContainsType(w, copy.get(i)) == -1) {
                copy.remove(i);
            } else {
                i++;
            }
        }
        
        //Nos aseguramos de que haya suficientes tipos de cada clase
        //Para ello usaremos un map y contaremos los distintos tipos de cada clase
        HashMap<WeaponType, Integer> mapCopy = new HashMap<>();
        HashMap<WeaponType, Integer> mapW = new HashMap<>();
        //Inicializamos el map
        for(WeaponType type: WeaponType.values()){
            mapCopy.put(type, 0);
            mapW.put(type, 0);
        }
        
        for (int j = 0; j < copy.size(); j++) {
            WeaponType get = copy.get(j);
            mapCopy.put(get, mapCopy.get(get)+1);
        }
        
        for (int j = 0; j < w.size(); j++) {
            WeaponType get = w.get(j).getType();
            mapW.put(get, mapW.get(get)+1);
        }
        
        //Limpiamos la lista
        for(WeaponType type: WeaponType.values()){
            while(mapCopy.get(type) > mapW.get(type)) {
                mapCopy.put(type, mapCopy.get(type)-1);
                weapons.remove(weapons.indexOf(type));
            }
        }
        
        return new Damage(copy, shields);
    }
    
    /**
     * intenta eliminar el tipo del arma pasada como parámetro de esa lista.
     * En otro caso simplemente decrementa en una unidad el contador de armas que deben ser eliminadas.
     * Ese contador no puedeser inferior a cero en ningún caso
     * @param w el arma
     */
    public void discardWeapon(Weapon w) {
        if(weapons == null){
            if(nWeapons > 0){
                nWeapons--;
            }
        } else {
            int index = weapons.indexOf(w.getType());
            if(index != -1){
                weapons.remove(index);
            }
        }
    }
    
    /**
     * Descarta un shieldBooster
     */
    public void discardShieldBooster() {
        nShields--;
        if(nShields < 0){
            nShields = 0;
        }
    }
    
    /**
     * @return true si el daño representado no tiene ningún efecto.
     * Esto quiere decir que no implica la pérdida de ningún tipo de accesorio (armas o potenciadores de escudo).
     */
    public boolean hasNoEffect() {
        if(weapons == null) {
            return (nWeapons == 0 && nShields == 0);
        }
        
        return (weapons.isEmpty() && nShields == 0);
    }

    public ArrayList<WeaponType> getWeapons() {
        if(weapons==null){
            return null;
        }
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
        if(weapons == null) {
            return "nShields: " + getNShields()+ "\nnWeapons: " + getNWeapons();
        }
        return "nShields: " + getNShields() + "\nWeapons: " + getWeapons().toString();
    }
    
    
    
}
