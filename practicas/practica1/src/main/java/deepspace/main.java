/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.HashMap;

/**
 *
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Loot l1 = new Loot(1, 2, 3, 4, 5);
       Loot l2 = new Loot(5,4,3,2,1);
       
        System.out.println("Loot:");
        System.out.println(l1.getNHangars());
        System.out.println(l1.getNMedals());
        System.out.println(l1.getNShields());
        System.out.println(l1.getNSupplies());
        System.out.println(l1.getNWeapons());
        System.out.println("");
        System.out.println(l2.getNHangars());
        System.out.println(l2.getNMedals());
        System.out.println(l2.getNShields());
        System.out.println(l2.getNSupplies());
        System.out.println(l2.getNWeapons());
        System.out.println("\n");
        
        
        SuppliesPackage s1 = new SuppliesPackage(2, 3, 4);
        SuppliesPackage s2 = new SuppliesPackage(s1);
        System.out.println("SuppliesPackage:");
        System.out.println(s1.getAmmoPower());
        System.out.println(s1.getFuelUnits());
        System.out.println(s1.getShieldPower());
        System.out.println("");
        System.out.println(s2.getAmmoPower());
        System.out.println(s2.getFuelUnits());
        System.out.println(s2.getShieldPower());
        System.out.println("\n");
        
        ShieldBooster b1 = new ShieldBooster("b1", 10, 2);
        System.out.println("ShieldBooster:");
        while(b1.getUses()!= 0){
            System.out.println(b1.useIt());
        }
        System.out.println("");
        System.out.println(b1.getUses());
        System.out.println(b1.getBoost());
        System.out.println("\n");
        
        Weapon w1 = new Weapon("w1", WeaponType.PLASMA, 3);
        System.out.println("Weapon:");
        System.out.println(w1.getType());
        System.out.println(w1.getUses());
        System.out.println(w1.power());
        System.out.println("");
        while(w1.getUses()!= 0){
            System.out.println(w1.useIt());
        }
        System.out.println("\n");
        
        
        Dice dado = new Dice();
        HashMap<Integer, Integer> map = new HashMap<>();
        int resultado = 0;
        System.out.println("Dado:");
        System.out.println("InitWithHangars:");
        map.put(0, 0);
        map.put(1, 0);
        for(int i = 0; i < 100; i++){
            resultado = dado.initWithNHangars();
            map.put(resultado, map.get(resultado) + 1);
        }
        System.out.println(map);
        
        System.out.println("InitWithShields:");
        map.put(0, 0);
        map.put(1, 0);
        for(int i = 0; i < 100; i++){
            resultado = dado.initWithNShields();
            map.put(resultado, map.get(resultado) + 1);
        }
        System.out.println(map);
        
        System.out.println("InitWithWeapons:");
        map.put(2, 0);
        map.put(1, 0);
        map.put(3,0);
        map.remove(0);
        for(int i = 0; i < 100; i++){
            resultado = dado.initWithNWeapons();
            map.put(resultado, map.get(resultado) + 1);
        }
        System.out.println(map);
        
        System.out.println("Who starts 5 players:");
        map.put(2, 0);
        map.put(1, 0);
        map.put(3,0);
        map.put(0, 0);
        map.put(4,0);
        for(int i = 0; i < 100; i++){
            resultado = dado.whoStarts(5);
            map.put(resultado, map.get(resultado) + 1);
        }
        System.out.println(map);
        
        System.out.println("firstShot:");
        HashMap<GameCharacter, Integer> map2 = new HashMap<>();
        map2.put(GameCharacter.SPACESTATION, 0);
        map2.put(GameCharacter.ENEMYSTARSHIP, 0);
        GameCharacter resultado2;
        for(int i = 0; i < 100; i++){
            resultado2 = dado.firstShot();
            map2.put(resultado2, map2.get(resultado2) + 1);
        }
        System.out.println(map2);
        
        System.out.println("Moves with 0.4:");
        HashMap<Boolean, Integer> map3 = new HashMap<>();
        map3.put(true, 0);
        map3.put(false, 0);
        Boolean resultado3;
        for(int i = 0; i < 100; i++){
            resultado3 = dado.spaceStationMoves(0.4f);
            map3.put(resultado3, map3.get(resultado3) + 1);
        }
        System.out.println(map3);
    }
    
}
