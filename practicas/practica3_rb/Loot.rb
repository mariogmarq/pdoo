#encoding: utf-8

#Clase que representa el botín que se obtiene al vencer a una nave enemiga.

require_relative 'lib/LootToUI'

module Deepspace
  
  class Loot

    #Constructor con parametros de la clase
    def initialize(nSupplies, nWeapons, nShields, nHangars, nMedals)
      @nSupplies = nSupplies
      @nWeapons = nWeapons
      @nShields = nShields
      @nHangars = nHangars
      @nMedals = nMedals
    end

    #Método consultor del número de paquetes de suministros
    def nSupplies
      @nSupplies
    end

    #Método consultor del número de armas
    def nWeapons
      @nWeapons
    end

    #Método consultor del número de potenciadores de escudo
    def nShields
      @nShields
    end

    #Método consultor del número de hangares
    def nHangars 
      @nHangars
    end

    #Método consultor del número de medallas
    def nMedals
      @nMedals
    end

    def to_s
      return "nSupplies: " + @nSupplies.to_s + "\nnWeapons: " + @nWeapons.to_s + "\nnShields: " + @nShields.to_s + "\nnHangars: " + @nHangars.to_s + "\nnMedals: " + @nMedals.to_s
    end

    def getUIversion
      LootToUI.new self
    end

  end


end
