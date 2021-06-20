/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import controller.Controller;
import deepspace.GameState;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author mario
 */
public class CurrentStation extends javax.swing.JPanel {
    private int nWeapons;
    private int nShields;

    /**
     * Creates new form CurrentStation
     */
    public CurrentStation() {
        initComponents();
    }
    
    private ArrayList<Integer> selectedPanel(JPanel panel){
        ArrayList<Integer> p = new ArrayList<>();
        int i = 0;
        for(Component cmp: panel.getComponents()){
            if(((Selectable)cmp).getSelected()){
                p.add(i);
            }
            ++i;
        }
        return p;
    }
    
    private ArrayList<Integer> selectedWeaponsHangar(){
        ArrayList<Integer> p = new ArrayList<>();
        for (int i=0; i<nWeapons; ++i){
            Component cmp = panelHangar.getComponent(i);
            if(((Weapon)cmp).getSelected()){
                p.add(i);
            }
        }
        return p;
    }
    
    private ArrayList<Integer> selectedShieldsHangar(){
        ArrayList<Integer> p = new ArrayList<>();
        for (int i=nWeapons; i<nWeapons+nShields; ++i){
            Component cmp = panelHangar.getComponent(i);
            if(((Shield)cmp).getSelected()){
                p.add(i-nWeapons);
            }
        }
        return p;
    }
    
    
    public void updateComponent(SpaceStationToUI s) {
        //Update botons
        botonDescartarEnHangar.setEnabled(Controller.getInstance().getState()==GameState.INIT||Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        botonDescartarHangar.setEnabled(Controller.getInstance().getState()==GameState.INIT||Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        botonDescartarMontados.setEnabled(Controller.getInstance().getState()==GameState.INIT||Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        botonEquipar.setEnabled(Controller.getInstance().getState()==GameState.INIT||Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        
        nombreJugador.setText(s.getName());
        valorAtaque.setText(Float.toString(s.getAmmoPower()));
        valorDefensa.setText(Float.toString(s.getShieldPower()));
        valorCombustible.setText(Float.toString(s.getFuelUnits()));
        valorMedallas.setText(Float.toString(s.getnMedals()));
        
        if(s.getPendingDamage()!=null) {
            damage.updateComponent(s.getPendingDamage());
        } else {
            damage.nilDamage();
        }
        
        //Update weapons
        panelArmas.removeAll();
        for(WeaponToUI w: s.getWeapons()) {
            View.GUI.Weapon cmp = new Weapon();
            cmp.updateComponent(w);
            cmp.setBorder(javax.swing.BorderFactory.createLineBorder(null));
            panelArmas.add(cmp);
        }
        
        //Update shields
        panelEscudos.removeAll();
        for(ShieldToUI shield: s.getShieldBoosters()) {
            View.GUI.Shield cmp = new Shield();
            cmp.updateComponent(shield);
            cmp.setBorder(javax.swing.BorderFactory.createLineBorder(null));
            panelEscudos.add(cmp);
        }
        
        panelHangar.removeAll();
        if(s.getHangar()!=null){
            panelHangar.setBorder(javax.swing.BorderFactory.createTitledBorder("Hangar con " + Integer.toString(s.getHangar().getMaxElements()) + " espacios"));
            nWeapons = s.getHangar().getWeapons().size();
            nShields = s.getHangar().getShieldBoosters().size();
            for(WeaponToUI w: s.getHangar().getWeapons()){
                View.GUI.Weapon cmp = new Weapon();
                cmp.updateComponent(w);
                cmp.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                panelHangar.add(cmp);
            }
            for(ShieldToUI shield: s.getHangar().getShieldBoosters()){
                View.GUI.Shield cmp = new Shield();
                cmp.updateComponent(shield);
                cmp.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                panelHangar.add(cmp);
            }
        }
        else{
            panelHangar.setBorder(javax.swing.BorderFactory.createTitledBorder("Sin hangar"));
            nWeapons = 0;
            nShields = 0;
        }
        
        
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        damage = new View.GUI.Damage();
        panelArmas = new javax.swing.JPanel();
        panelEscudos = new javax.swing.JPanel();
        panelHangar = new javax.swing.JPanel();
        botonEquipar = new javax.swing.JButton();
        botonDescartarMontados = new javax.swing.JButton();
        botonDescartarEnHangar = new javax.swing.JButton();
        botonDescartarHangar = new javax.swing.JButton();
        nombreJugador = new javax.swing.JLabel();
        etiquetaAtaque = new javax.swing.JLabel();
        etiquetaDefensa = new javax.swing.JLabel();
        etiquetaCombustible = new javax.swing.JLabel();
        etiquetaMedallas = new javax.swing.JLabel();
        valorAtaque = new javax.swing.JLabel();
        valorDefensa = new javax.swing.JLabel();
        valorCombustible = new javax.swing.JLabel();
        valorMedallas = new javax.swing.JLabel();

        damage.setBorder(javax.swing.BorderFactory.createTitledBorder("Castigo pendiente\n"));

        panelArmas.setBorder(javax.swing.BorderFactory.createTitledBorder("Armas"));

        panelEscudos.setBorder(javax.swing.BorderFactory.createTitledBorder("Escudos"));

        panelHangar.setBorder(javax.swing.BorderFactory.createTitledBorder("Hangar"));

        botonEquipar.setText("Equipar");
        botonEquipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEquiparActionPerformed(evt);
            }
        });

        botonDescartarMontados.setText("Descartar Montados");
        botonDescartarMontados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDescartarMontadosActionPerformed(evt);
            }
        });

        botonDescartarEnHangar.setText("Descartar en Hangar");
        botonDescartarEnHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDescartarEnHangarActionPerformed(evt);
            }
        });

        botonDescartarHangar.setText("Descartar Hangar");
        botonDescartarHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDescartarHangarActionPerformed(evt);
            }
        });

        nombreJugador.setText("valorNombre");

        etiquetaAtaque.setText("Potencia de Ataque: ");

        etiquetaDefensa.setText("Potencia de Defensa: ");

        etiquetaCombustible.setText("Nivel de Combustible: ");

        etiquetaMedallas.setText("Medallas: ");

        valorAtaque.setText("jLabel1");

        valorDefensa.setText("jLabel2");

        valorCombustible.setText("jLabel1");

        valorMedallas.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelArmas, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelEscudos, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaAtaque)
                                            .addComponent(etiquetaDefensa)
                                            .addComponent(etiquetaCombustible)
                                            .addComponent(etiquetaMedallas))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(valorCombustible)
                                            .addComponent(valorDefensa)
                                            .addComponent(valorAtaque)
                                            .addComponent(valorMedallas))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(nombreJugador))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(botonEquipar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonDescartarMontados, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonDescartarEnHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(botonDescartarHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(nombreJugador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaAtaque)
                    .addComponent(valorAtaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDefensa)
                    .addComponent(valorDefensa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCombustible)
                    .addComponent(valorCombustible))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMedallas)
                    .addComponent(valorMedallas))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(damage, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelArmas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelEscudos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(panelHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEquipar)
                    .addComponent(botonDescartarMontados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonDescartarEnHangar)
                    .addComponent(botonDescartarHangar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonEquiparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEquiparActionPerformed
        Controller.getInstance().mount(selectedWeaponsHangar(), selectedShieldsHangar());
        updateComponent(Controller.getInstance().getUIversion().getCurrentStation());
    }//GEN-LAST:event_botonEquiparActionPerformed

    private void botonDescartarHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDescartarHangarActionPerformed
        Controller.getInstance().discardHangar();
        this.updateComponent(Controller.getInstance().getUIversion().getCurrentStation());
    }//GEN-LAST:event_botonDescartarHangarActionPerformed

    private void botonDescartarMontadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDescartarMontadosActionPerformed
        Controller.getInstance().discard(Controller.WEAPON, selectedPanel(panelArmas), new ArrayList<Integer>());                                               
        Controller.getInstance().discard(Controller.SHIELD, new ArrayList<Integer>(), selectedPanel(panelEscudos));
        this.updateComponent(Controller.getInstance().getUIversion().getCurrentStation());
    }//GEN-LAST:event_botonDescartarMontadosActionPerformed

    private void botonDescartarEnHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDescartarEnHangarActionPerformed
        Controller.getInstance().discard(Controller.HANGAR, selectedWeaponsHangar(), selectedShieldsHangar());
        this.updateComponent(Controller.getInstance().getUIversion().getCurrentStation());
    }//GEN-LAST:event_botonDescartarEnHangarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDescartarEnHangar;
    private javax.swing.JButton botonDescartarHangar;
    private javax.swing.JButton botonDescartarMontados;
    private javax.swing.JButton botonEquipar;
    private View.GUI.Damage damage;
    private javax.swing.JLabel etiquetaAtaque;
    private javax.swing.JLabel etiquetaCombustible;
    private javax.swing.JLabel etiquetaDefensa;
    private javax.swing.JLabel etiquetaMedallas;
    private javax.swing.JLabel nombreJugador;
    private javax.swing.JPanel panelArmas;
    private javax.swing.JPanel panelEscudos;
    private javax.swing.JPanel panelHangar;
    private javax.swing.JLabel valorAtaque;
    private javax.swing.JLabel valorCombustible;
    private javax.swing.JLabel valorDefensa;
    private javax.swing.JLabel valorMedallas;
    // End of variables declaration//GEN-END:variables
}
