/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 *  tiene la responsabilidad de tomar todas las decisiones que dependen del azar en el juego.
 */
class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private Random generator;
    
    Dice() {
        generator = new Random();
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
    }
    
    /**
        Este método determina el número de hangares que recibirá una estación espacial al ser creada
        @return 0 con probabilidad o 1 en otro caso
    */
    public int initWithNHangars() {
        if(generator.nextFloat() < NHANGARSPROB){
            return 0;
        }
        return 1;
    }
    
    /**
     determina el número de armas que recibirá una estación espacial al ser creada
     @return 1, 2 o 3
     */
    public int initWithNWeapons(){
        float guess = generator.nextFloat();
        if(guess < NWEAPONSPROB){
            return 1;
        } else if(guess < 2* NWEAPONSPROB){
            return 2;
        } else {
            return 3;
        }
    }
    
    /**
     * determina el número de potenciadores de escudo que recibirá una estación espacial alser creada
     * @return 0 o 1
     * */
    public int initWithNShields() {
        if(generator.nextFloat() < NSHIELDSPROB){
            return 0;
        }
        
        return 1;
    }
    
    /**
     * determina el jugador (su índice) que iniciará la partida
     * @return numero en rango [0,nplayers-1]
     */
    public int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers);
    }
    
    /**
     * Este método determina quién (de los dos tipos de personajes deljuego) dispara primero en un combate
     * @return GameCharacter.SPACESTATION o GameCharacter.ENEMYSTARSHIP
     * @see GameCharacter
     */
    public GameCharacter firstShot(){
        if(generator.nextFloat()<FIRSTSHOTPROB){
            return GameCharacter.SPACESTATION;
        } else {
            return GameCharacter.ENEMYSTARSHIP;
        }
    }
    
    /**
     * determina si la estaciónespacial se moverá para esquivar un disparo
     * @return un booleano
     */
    public boolean spaceStationMoves(float speed) {
        return (generator.nextFloat() < speed);
    }
}
