/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author mariogmarq
 */
import View.DeepSpaceView;
import deepspace.EnemyToUI;
import deepspace.GameUniverse;

public class ControladorExamen {

    final static ControladorExamen instance = new ControladorExamen();
    
    private GameUniverse game;
    private DeepSpaceView view;
    
    private ControladorExamen() {
    }
    
    public static ControladorExamen getInstance() {
        return instance;
    }
    
    public void setModelView(GameUniverse aGame, DeepSpaceView aView) {
        game = aGame;
        view = aView;
    }
    
    public void start() {
        game.init(view.readNamePlayers());
        view.updateView();
        view.showView();
    }
    
    public void finish(int i) {
        if (view.confirmExitMessage()) {
            System.exit(i);
        }
    }
    
    public EnemyToUI dameUnEnemy() {
        return game.dameUnEnemy();
    }
    
}
