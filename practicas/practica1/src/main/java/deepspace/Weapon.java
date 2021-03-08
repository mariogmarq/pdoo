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
    
    /**
     * constructor de la clase
     * @param name nombre del arma
     * @param type tipo del arma
     * @see WeaponType
     * @param uses cantidad de usos del arma
     */
    Weapon(String name, WeaponType type, int uses) {
        this.name = name;
        this.type = type;
        this.uses = uses;
    }
    
    /**
     * Constructor copia
     * @param w instancia a copiar
     */
    Weapon(Weapon w) {
        this.name = w.name;
        this.type = w.type;
        this.uses = w.uses;
    }
    
    /**
     * @return el tipo del arma
     * @see WeaponType
     */
    public WeaponType getType(){
        return type;
    }
    
    /**
     * @return usos restantes del arma
     */
    public int getUses() {
        return uses;
    }
    
    /**
     * @return potencia del arma
     */
    public float power() {
        return type.getPower();
    }
    
    /**
     * Usa el arma disminuyendo sus usos en uno
     * @return el poder del arma o 1.0 en caso de que no queden usos
     */
    public float useIt(){
        if(uses > 0){
            uses--;
            return power();
        }
        
        return 1.0f;
    }
}
