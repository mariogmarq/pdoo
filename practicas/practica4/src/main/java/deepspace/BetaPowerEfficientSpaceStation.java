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
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private final static float EXTRAEFFICIENCY = 1.2f;
    
    public BetaPowerEfficientSpaceStation(SpaceStation station) {
        super(station);
    }
    
    @Override
    public float fire() {
        float eficiencia = (new Dice()).extraEfficiency() ? EXTRAEFFICIENCY : 1.0f;
        
        return super.fire() * eficiencia;
        
        
    }
}
