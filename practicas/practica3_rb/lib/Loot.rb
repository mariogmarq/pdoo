#encoding: utf-8

#Clase que representa el botín que se obtiene al vencer a una nave enemiga.

require_relative 'LootToUI'

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

    attr_reader :nSupplies, :nHangars, :nMedals, :nWeapons, :nShields

    def to_s
      "nSupplies: " + @nSupplies.to_s + "\nnWeapons: " + @nWeapons.to_s + "\nnShields: " + @nShields.to_s + "\nnHangars: " + @nHangars.to_s + "\nnMedals: " + @nMedals.to_s
    end

    def getUIversion
      LootToUI.new self
    end

  end


end
