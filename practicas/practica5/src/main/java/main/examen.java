/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import View.DeepSpaceView;
import View.Examen.App;
import controller.ControladorExamen;
import deepspace.GameUniverse;

/**
 *
 * @author mariogmarq
 */
public class examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      DeepSpaceView ui;
      GameUniverse game = new GameUniverse();
      ui = new App();
      ControladorExamen controller = ControladorExamen.getInstance();
      controller.setModelView(game,ui);
      controller.start();
    }
    
}
