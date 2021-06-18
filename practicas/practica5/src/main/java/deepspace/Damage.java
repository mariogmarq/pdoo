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
abstract public class Damage {
    private int nShields;
    
    Damage(int s){nShields = s;}
    
    public int getNShields(){return nShields;}
    
    
    
    abstract Damage copy();
    
    abstract DamageToUI getUIversion();
    
  
    
    protected int adjustShields(ArrayList<ShieldBooster> s){
        return Integer.min(s.size(), getNShields());
    }
    
    /**
     * Devuelve una versión ajustada del objeto a las colecciones de armas y potenciadores de escudos suministradas como parámetro.
     * @param w armas
     * @param s potenciadores
     * @return version ajustada del objeto
     */
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    
    
    /**
     * intenta eliminar el tipo del arma pasada como parámetro de esa lista.
     * En otro caso simplemente decrementa en una unidad el contador de armas que deben ser eliminadas.
     * Ese contador no puedeser inferior a cero en ningún caso
     * @param w el arma
     */
    
    abstract public void discardWeapon(Weapon w);
    
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
    public boolean hasNoEffect(){
        return getNShields() == 0;
    }


    @Override
    public String toString(){
        return "nShields: " + getNShields();
    }
    
    
    
}
