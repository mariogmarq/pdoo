/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 * Representa un hangar
 */
public class Hangar {
    private int maxElements;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;

    Hangar(int capacity) {
        this.maxElements = capacity;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
    }
    
    Hangar(Hangar h) {
       this.maxElements = h.getMaxElements();
       this.weapons = h.getWeapons();
       this.shieldBoosters = h.getShieldBoosters();
    }
    
   HangarToUI getUIversion() {
        return new HangarToUI(this);
    }
    
   /**
    * @return true si aún hay espacio para añadir elementos y que por lotanto no se ha llegado a la capacidad máxima
    */
   private boolean spaceAvailable() {
       return (weapons.size() + shieldBoosters.size()) < maxElements;
   }
   /**
    * Agrega w al hangar
    * @param w el arma a agregar
    * @return si se ha podido agregar por el tamanio
    */
   public boolean addWeapon(Weapon w) {
       if (this.spaceAvailable()) {
           weapons.add(w);
           return true;
       }
       
       return false;
   }
   
   /**
    * Agrega s al hangar
    * @param s el shieldbooster a agregar
    * @return si se ha podido agregar por el tamanio
    */
   public boolean addShieldBooster(ShieldBooster s) {
       if (this.spaceAvailable()) {
           shieldBoosters.add(s);
           return true;
       }
       
       return false;
   }
   
   /**
    * @return capacidad del hangar
    */
    public int getMaxElements() {
        return maxElements;
    }

    /**
     * @return escudos del hangar
     */
    public ArrayList<ShieldBooster> getShieldBoosters() {
        return new ArrayList<>(shieldBoosters);
    }
    
    /**
     * @return armas del hangar
     */
    public ArrayList<Weapon> getWeapons() {
        return new ArrayList<>(weapons);
    }
   
    /**
     * Elimina el escudo número s del hangar y la devuelve, siempre que esta exista.
     * @param s indice del arma
     * @return  Si el índice suministrado es incorrecto devuelve null, devuelve el escudo
     */
    public ShieldBooster removeShieldBooster(int s){
        if(s >= shieldBoosters.size() || s < 0) {
             return null;
         }
         
        return shieldBoosters.remove(s);
    } 
    
    /**
     * Elimina el arma número w del hangar y la devuelve, siempre que esta exista.
     * @param w indice del arma
     * @return Si el índice suministrado es incorrecto devuelve null, devuelve el arma
     */
    public Weapon removeWeapon(int w){
         if(w >= weapons.size() || w < 0) {
             return null;
         }
         
        return weapons.remove(w);
    }

    @Override
    public String toString() {
        return "maxElements: " + getMaxElements() + "\nweapons: " + getWeapons().toString() + "\nshieldBoosters: " + getShieldBoosters().toString();
    }
    
    
    
}
