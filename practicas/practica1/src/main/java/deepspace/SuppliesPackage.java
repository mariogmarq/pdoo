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

    SuppliesPackage(float ammoPower, float fuelUnits, float shieldPower) {
        this.ammoPower = ammoPower;
        this.fuelUnits = fuelUnits;
        this.shieldPower = shieldPower;
    }
   
    SuppliesPackage(SuppliesPackage s){
        this.ammoPower = s.ammoPower;
        this.fuelUnits = s.fuelUnits;
        this.shieldPower = s.shieldPower;
    }
   
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
}
