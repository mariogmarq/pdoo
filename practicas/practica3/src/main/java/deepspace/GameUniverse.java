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
public class GameUniverse {
    
    private static final int WIN = 10;
    
    private int currentStationIndex;
    private int turns;
    
    private GameStateController gameState;
    private Dice dice;
    
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations;
    private EnemyStarShip currentEnemy;
    
    public GameUniverse(){
        gameState = new GameStateController();
        turns = 0;
        dice = new Dice();
    }
    
   
    
    public GameState getState(){
        return gameState.getState();
    }
    
    public boolean haveAWinner(){
        return (currentStation.getNMedals()>=WIN);
    }
    
    public void discardHangar(){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }
    
    public void discardShieldBooster(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardWeapon(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }
    
    
    public void mountShieldBooster(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.mountShieldBooster(i);
        }
    }
    
    public void mountWeapon(int i){
        if(getState()==GameState.INIT || getState()==GameState.AFTERCOMBAT){
            currentStation.mountWeapon(i);
        }
    }
    
    public void init(ArrayList<String>names){
        GameState state = gameState.getState();
        if(state==GameState.CANNOTPLAY){
            spaceStations = new ArrayList<>();
            CardDealer dealer = CardDealer.getInstance();
            
            for(String name: names) {
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(name, supplies);
                spaceStations.add(station);
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                
                Loot lo = new Loot(0, nw, ns, nh, 0);
                station.setLoot(lo);
            }
            
            currentStationIndex = dice.whoStarts(names.size());
            currentStation = spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            gameState.next(turns, spaceStations.size());
        }
    }
    
    public boolean nextTurn(){
        GameState state = gameState.getState();
        if(state == GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            if(stationState){
                currentStationIndex = (currentStationIndex + 1) % spaceStations.size();
                turns++;
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                gameState.next(turns, spaceStations.size());
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
    
    public CombatResult combat(){
        GameState state = gameState.getState();
        if(state==GameState.BEFORECOMBAT || state==GameState.INIT){
            CombatResult result = combat(currentStation, currentEnemy);
            gameState.next(turns, spaceStations.size());
            return result;
        }

        return CombatResult.NOCOMBAT;
    }
    
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch = dice.firstShot();
        boolean enemyWins = false;
        
        if(ch==GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);
            
            if(result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.recieveShot(fire);
                enemyWins = (result==ShotResult.RESIST);
            } else {
                enemyWins = true;
            }
        } else {
            float fire = station.fire();
            ShotResult result = enemy.recieveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if(enemyWins){
            float s = station.getSpeed();
            boolean moves = dice.spaceStationMoves(s);
            if(!moves) {
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage);
                return CombatResult.ENEMYWINS;
            }
            
            return CombatResult.STATIONESCAPES;
        }
        
        Loot aLoot = enemy.getLoot();
        station.setLoot(aLoot);
        
        return CombatResult.STATIONWINS;
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(currentStation, currentEnemy);
    }
    
    
}
