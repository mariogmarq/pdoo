/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * representa a un paquete de suministros para una estación espacial. Puede contenerarmamento, combustible y/o energía para los escudos
 */
public class SuppliesPackage {
   private float ammoPower;
   private float fuelUnits;
   private float shieldPower;

   /**
    * constructor de la clase
    * @param ammoPower poder de municion
    * @param fuelUnits cantidad de combustible
    * @param shieldPower poder de escudo
    */
    SuppliesPackage(float ammoPower, float fuelUnits, float shieldPower) {
        this.ammoPower = ammoPower;
        this.fuelUnits = fuelUnits;
        this.shieldPower = shieldPower;
    }
   
    /**
     * constructor copia
     * @param s instancia de la clase SuppliesPackage a ser copiada
     */
    SuppliesPackage(SuppliesPackage s){
        this.ammoPower = s.ammoPower;
        this.fuelUnits = s.fuelUnits;
        this.shieldPower = s.shieldPower;
    }
   
    /**
     * @return el poder de municion
     */
    public float getAmmoPower(){
        return ammoPower;
    }
    
    /**
     * @return cantidad de combustible
     */
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    /**
     * @return poder del escudo
     */
    public float getShieldPower(){
        return shieldPower;
    }
}
