#encoding: utf-8

require_relative 'Damage'
require_relative 'NumericDamageToUI'

module Deepspace
  class NumericDamage < Damage
    public_class_method :new

    attr_reader :nShields, :nWeapons


    def initialize(w, s)
      @nWeapons = w
      super s
    end

    def self.newCopy(d)
      new(d.nWeapons, d.nShields)
    end

    def copy
      NumericDamage.newCopy self
    end

    def discardWeapon(w)
      if nWeapons > 0
        @nWeapons -= 1
      else
        @nWeapons = 0
      end
    end

    def hasNoEffect
      nWeapons.zero? and super
    end

    def adjust(w, s)
      limit_nshields = adjustShields s
      limit_nweapons = [w.length, nWeapons].min
      NumericDamage.new(limit_nweapons, limit_nshields)
    end

    def getUIversion
      NumericDamageToUI.new(self)
    end

    def to_s
      "nWeapons: " + @nWeapons.to_s + "\n" + super
    end

  end
end
