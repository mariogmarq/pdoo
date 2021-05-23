/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author mario
 */
public class PowerEfficientSpaceStation extends SpaceStation{
    private final static float EFFICIENCYFACTOR = 1.1f;

    public PowerEfficientSpaceStation(SpaceStation station) {
        super(station);
    }
    
    @Override
    public Transformation setLoot(Loot loot) {
        Transformation t = super.setLoot(loot);
        if(t==Transformation.GETEFFICIENT){
            return t;
        }
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public float fire() {
        return super.fire() * EFFICIENCYFACTOR;
    }
    
    @Override
    public float protection() {
        return super.protection() * EFFICIENCYFACTOR;
    }
    
    @Override
    public SpaceStationToUI getUIversion() {
        return new PowerEfficientSpaceStationToUI(this);
    }
    
}
