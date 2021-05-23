/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author inmagalvez
 */
public class SpaceCity extends SpaceStation {
    private ArrayList<SpaceStation> collaborators;
    private SpaceStation base;
    
    //Preguntar si hay que añadir base a collaborators y si hay que inicializar base
    SpaceCity(SpaceStation base, ArrayList<SpaceStation> rest){
        super(base);
        collaborators = rest;
        //collaborators.add(0, base);
        this.base = base;
    }
    
    ArrayList<SpaceStation> getCollaborators(){
        return new ArrayList<>(collaborators);
    }
    
    @Override
    public float fire(){
        float res = 0.0f;
        for(SpaceStation i : collaborators){
            res += i.fire();
        }
        return res;
    }
    
    @Override
    public float protection(){
        float res = 0.0f;
        for(SpaceStation i : collaborators){
            res += i.protection();
        }
        return res;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public SpaceStationToUI getUIversion() {
        return new SpaceCityToUI(this);
    }

}
