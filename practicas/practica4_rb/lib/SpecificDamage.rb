#encoding: utf-8

require_relative 'Damage'
require_relative 'SpecificDamageToUI'

module Deepspace
  class SpecificDamage < Damage
    public_class_method :new

    attr_reader :weapons, :nShields

    def initialize(w, s)
      @weapons = w.clone
      super s
    end

    def self.newCopy(d)
      new(d.weapons, d.nShields)
    end

    def discardWeapon(w)
      indice = weapons.index(w.type)
      if indice != nil
        @weapons.delete_at(indice)
      end
    end

    def hasNoEffect
      !(!weapons.empty? || nShields > 0)
    end

    private def arrayContainsType(w, t)
      indice = w.index { |x| x.type == t }
      if indice != nil
        indice
      else
        -1
      end
    end

    def getUIversion
      SpecificDamageToUI.new(self)
    end


    def copy
      SpecificDamage.newCopy self
    end

    def to_s
      "weapons: " + @weapons.to_s + "\nnShields: " + @nShields.to_s + "\n"
    end

    def adjust(w, s)
      limit_nshields = [s.length, nShields].min
      result = []
      w_aux = w.clone
      weapons.each do |element|
        indice = arrayContainsType(w_aux, element)

        if indice != -1
          result.push(element)
          w_aux.delete_at(indice)
        end
      end

      SpecificDamage.new(result, limit_nshields)
    end
  end
end
