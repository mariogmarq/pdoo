/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * representa a los potenciadores de escudo que pueden tener las estaciones espaciales.
 */
public class ShieldBooster implements CombatElement, Copyable<ShieldBooster> {
    private String name;
    private float boost;
    private int uses;

    /**
     * constructor de la clase
     * @param name nombre del potenciador
     * @param boost potencia del potenciador
     * @param uses usos del potenciador
     */
    ShieldBooster(String name, float boost, int uses) {
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    
    /**
     * Constructor copia
     * @param s instancia de clase ShieldBooster a ser copiada
     */
    ShieldBooster(ShieldBooster s) {
        this (s.name, s.getBoost(), s.getUses());
    }
    
    /**
     * @return potencia del potenciador
     */
    public float getBoost() {
        return boost;
    }
    
    /**
     * @return cantidad de usos restantes
     */
    @Override
    public int getUses() {
        return uses;
    }
    
    /**
     * usa el potenciador disminuyendo el numero de usos
     * @return el valor del potenciador o 1 en caso de que no queden usos
     */
    @Override
    public float useIt(){
        if(uses > 0){
            uses--;
            return getBoost();
        }
        
        return 1.0f;
    }
    
    ShieldToUI getUIversion() {
        return new ShieldToUI(this);
    }

    @Override
    public String toString() {
        return "name: " + name + "\nboost: " + boost + "\nuses: " + uses;
    }

    @Override
    public ShieldBooster copy() {
        return new ShieldBooster(this);
    }
    
    
}
