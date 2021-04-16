/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *  representa a los tipos de armas del juego y no será simplemente una lista devalores, sino que tendrá cierta funcionalidad adicional.
 * En este caso, cada valor posible del tipoenumerado tendrá asociado un valor numérico concreto igual a la potencia
 * de disparo de cada tipode arma.
 */
public enum WeaponType {
    LASER(2.0f), MISSILE(3.0f), PLASMA(4.0f);
    
    private float power;

    WeaponType(float power) {
        this.power = power;
    }
    
    float getPower() {
        return this.power;
    }
}
