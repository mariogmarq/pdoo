/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * Representa una Estacion Espacial
 */
public class SpaceStation {
    
    private static final int MAXFUEL = 100;
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f;
    
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    private Damage pendingDamage;
    private ArrayList<Weapon>weapons;
    private ArrayList<ShieldBooster>shieldBoosters;
    private Hangar hangar;
    
    
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower = supplies.getAmmoPower();
        fuelUnits = supplies.getFuelUnits();
        shieldPower = supplies.getShieldPower();
        nMedals = 0;
        pendingDamage = null;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
        hangar = null;
    }
    
    public float getAmmoPower(){return ammoPower;}
    public float getFuelUnits(){return fuelUnits;}
    public String getName(){return name;}
    public int getNMedals(){return nMedals;}
    public float getShieldPower(){return shieldPower;}
    public Damage getPendingDamage(){return pendingDamage;}
    public ArrayList<Weapon> getWeapons(){return weapons;}
    public ArrayList<ShieldBooster> getShieldBoosters(){return shieldBoosters;}
    public Hangar getHangar(){return hangar;}
    
    public float getSpeed(){
        return getFuelUnits()/MAXFUEL;
    }
    
    private void assignFuelValue(float f){
        if (f<= MAXFUEL)
            fuelUnits = f;
    }
    
    private void cleanPendingDamage(){
        if(pendingDamage!=null && pendingDamage.hasNoEffect())
            pendingDamage = null;
    }
    
    boolean receiveWeapon(Weapon w){
        if (hangar!=null)
            return hangar.addWeapon(w);
        else
            return false;
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if(hangar!=null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    public void receiveHangar(Hangar h){
        if(hangar==null)
            hangar = h;
    }
    
    public void discardHangar(){hangar = null;}
    
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        fuelUnits += s.getFuelUnits();
        shieldPower += s.getShieldPower();
    }
    
    //Comprobar
    public void setPendingDamage (Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    public void mountWeapon(int i){
        if (hangar!=null){
            Weapon aux = hangar.removeWeapon(i);
            if (aux!=null)
                weapons.add(aux);
        }
    }
    
    public void mountShieldBooster(int i){
        if (hangar!=null){
            ShieldBooster aux = hangar.removeShieldBooster(i);
            if (aux!=null)
                shieldBoosters.add(aux);
        }
    }
    
    
    public void discardWeaponInHangar(int i){
        if(hangar!=null)
            hangar.removeWeapon(i);
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(hangar!=null)
            hangar.removeShieldBooster(i);
    }
    
    public void move(){
        fuelUnits -= getSpeed();
        if (getFuelUnits() < 0)
            fuelUnits = 0;        
    }
    
    public boolean validState(){
        if(pendingDamage!=null){
            if(pendingDamage.hasNoEffect())
                return true;
            else
                return false;
        }
        else
            return true;
    }
    
    public void cleanUpMountedItems(){
        weapons.removeIf(n -> n.getUses()==0);
        shieldBoosters.removeIf(n -> n.getUses()==0);
    }
    
    
    public float fire(){
        throw new UnsupportedOperationException();
    }
    
    public float protection(){
        throw new UnsupportedOperationException();
    }
    
    public ShotResult receiveShot(float shot){
        throw new UnsupportedOperationException();
    }
    
    public void setLoot(Loot loot){
        throw new UnsupportedOperationException();
    }
    
    public void discardWeapon(int i){
        throw new UnsupportedOperationException();
    }
    
    public void discardShieldBooster(int i){
        throw new UnsupportedOperationException();
    }
    
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
}
