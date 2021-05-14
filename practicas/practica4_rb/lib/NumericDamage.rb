#encoding: utf-8

require_relative 'Damage'
require_relative 'NumericDamageToUI'

module Deepspace
  class NumericDamage < Damage
    public_class_method :new

    attr_reader :nShields, :nWeapons

    def copy
      NumericDamage.newCopy self
    end

    def initialize(w, s)
      @nWeapons = w
      super s
    end

    def self.newCopy(d)
      new(d.nWeapons, d.nShields)
    end

    def discardWeapon(w)
      if nWeapons > 0
        @nWeapons -= 1
      else
        @nWeapons = 0
      end
    end

    def hasNoEffect
      !(nWeapons > 0 or nShields > 0)
    end

    def adjust(w, s)
      limit_nshields = [s.length, nShields].min
      limit_nweapons = [w.length, nWeapons].min
      NumericDamage.new(limit_nweapons, limit_nshields)
    end

    def getUIversion
      NumericDamageToUI.new(self)
    end

    def to_s
      "nWeapons: " + @nWeapons.to_s + "\nnShields: " + @nShields.to_s + "\n"
    end

  end
end
