/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * representa el botín que se obtiene al vencer a una nave enemiga.
 * Puede incluir cantidadesque representen un número de paquetes de suministros, armas,
 * potenciadores de escudo, hangares y/o medallas.
 */
class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;

    /**
     * Construye un Loot con los siguientes parametros
     * @param nSupplies numero de supplies que tiene el loot
     * @param nWeapons numero de armas que contiene
     * @param nShields numero de escudos que contiene
     * @param nHangars numero de hangares que contiene
     * @param nMedals numero de medallas que contiene
     */
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
    }
    
    /**
     * @return el numero de supplies que contiene el loot
     */
    public int getNSupplies(){
        return nSupplies;
    }
    /**
     * @return el numero de armas que contiene el loot
     */
    public int getNWeapons() {
        return nWeapons;
    }
    
    /**
     * @return el numero de escudos que contiene el loot
     */
    public int getNShields(){
        return nShields;
    }
    
    /**
     * @return el numero de hangares que contiene el loot
     */
    public int getNHangars() {
        return nHangars;
    }
    
    /**
     * @return el numero de medallas que contiene el loot
     */
    public int getNMedals(){
        return nMedals;
    }
    
    public LootToUI getUIversion() {
        return new LootToUI(this);
    }

    @Override
    public String toString() {
        return "nSupplies: " + nSupplies + "\nnWeapons: " + nWeapons + "\nnShields: " + nShields
                + "\nnHangars: " + nHangars;
    }
    
    
}
