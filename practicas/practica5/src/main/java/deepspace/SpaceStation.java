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
public class SpaceStation implements SpaceFighter {
    
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
        assignFuelValue(supplies.getFuelUnits());
        shieldPower = supplies.getShieldPower();
        nMedals = 0;
        pendingDamage = null;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
        hangar = null;
    }
    
    SpaceStation(SpaceStation station){
        this(station.name, new SuppliesPackage(station.ammoPower, station.fuelUnits, station.shieldPower));
        nMedals = station.nMedals;
        pendingDamage = station.pendingDamage;
        weapons = new ArrayList<>(station.weapons);
        shieldBoosters = new ArrayList<>(station.shieldBoosters);
        hangar = station.hangar;
    }
    
    public float getAmmoPower(){return ammoPower;}
    public float getFuelUnits(){return fuelUnits;}
    public String getName(){return name;}
    public int getNMedals(){return nMedals;}
    public float getShieldPower(){return shieldPower;}
    public Damage getPendingDamage(){return pendingDamage;}
    public ArrayList<Weapon> getWeapons(){return new ArrayList<>(weapons);}
    public ArrayList<ShieldBooster> getShieldBoosters(){return new ArrayList<>(shieldBoosters);}
    public Hangar getHangar(){return hangar;}
    
    public float getSpeed(){
        return getFuelUnits()/MAXFUEL;
    }
    
    private void assignFuelValue(float f){
        
        fuelUnits = Float.min(f, MAXFUEL);
    }
    
    private void cleanPendingDamage(){
        if(pendingDamage!=null && pendingDamage.hasNoEffect()) {
            pendingDamage = null;
        }
    }
    
    boolean receiveWeapon(Weapon w){
        if (hangar!=null) {
            return hangar.addWeapon(w);
        } else {
            return false;
        }
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if(hangar!=null) {
            return hangar.addShieldBooster(s);
        } else {
            return false;
        }
    }
    
    public void receiveHangar(Hangar h){
        if(hangar==null) {
            hangar = h;
        }
    }
    
    public void discardHangar(){hangar = null;}
    
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        assignFuelValue(fuelUnits + s.getFuelUnits());
        shieldPower += s.getShieldPower();
    }
    
    //Comprobar
    public void setPendingDamage (Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    public void mountWeapon(int i){
        if (hangar!=null){
            Weapon aux = hangar.removeWeapon(i);
            if (aux!=null) {
                weapons.add(aux);
            }
        }
    }
    
    public void mountShieldBooster(int i){
        if (hangar!=null){
            ShieldBooster aux = hangar.removeShieldBooster(i);
            if (aux!=null) {
                shieldBoosters.add(aux);
            }
        }
    }
    
    
    public void discardWeaponInHangar(int i){
        if(hangar!=null) {
            hangar.removeWeapon(i);
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(hangar!=null) {
            hangar.removeShieldBooster(i);
        }
    }
    
    public void move(){
        fuelUnits -= getSpeed();
        if (getFuelUnits() < 0) {
            fuelUnits = 0;
        }        
    }
    
    public boolean validState(){
        if(pendingDamage!=null){
            return pendingDamage.hasNoEffect();
        }
        else {
            return true;
        }
    }
    
    public void cleanUpMountedItems(){
        weapons.removeIf(n -> n.getUses()==0);
        shieldBoosters.removeIf(n -> n.getUses()==0);
    }
    
    @Override
    public float fire(){
        float factor = 1.0f;
        for(int i=0; i<weapons.size(); ++i){
            Weapon w = weapons.get(i);
            factor *= w.useIt();
        }
        return getAmmoPower()*factor;
    }
    
    @Override
    public float protection(){
        float factor = 1.0f;
        for(int i=0; i<shieldBoosters.size(); ++i){
            ShieldBooster s = shieldBoosters.get(i);
            factor *= s.useIt();
        }
        return getShieldPower()*factor;
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        float myProtection = protection();
        if(myProtection>=shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower = Math.max(0.0f, shieldPower);
            return ShotResult.RESIST;
        }
        else{
            shieldPower = 0.0f;
            return ShotResult.DONOTRESIST;
        }
    }
    
    public Transformation setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        
        if(loot.getNHangars()>0){
            Hangar h = dealer.nextHangar();
            receiveHangar(h);
        }
        
        for(int i=0; i<loot.getNSupplies(); ++i){
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        for(int i=0; i<loot.getNWeapons(); ++i){
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        for(int i=0; i<loot.getNShields(); ++i){
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        nMedals += loot.getNMedals();
        
        if(loot.getEfficient())
            return Transformation.GETEFFICIENT;
        if(loot.spaceCity())
            return Transformation.SPACECITY;
        
        return Transformation.NOTRANSFORM;
    }
    
    public void discardWeapon(int i){
        int size = weapons.size();
        if(i>=0 && i<size){
            Weapon w = weapons.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }
    
    public void discardShieldBooster(int i){
        int size = shieldBoosters.size();
        if(i>=0 && i<size){
            shieldBoosters.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
}
