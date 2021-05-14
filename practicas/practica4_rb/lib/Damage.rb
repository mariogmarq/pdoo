#encoding: utf-8

#Clase que representa el daño producido a una estación espacial por una nave enemiga cuando se pierde un combate

module Deepspace

  class Damage

    private_class_method :new

    def initialize(s)
      @nShields = s
    end

    #Constructor de copia
    def self.copy()
    end

    def discardWeapon(w) end

    def discardShieldBooster
      if nShields > 0
        @nShields -= 1
      else
        @nShields = 0
      end
    end

    def hasNoEffect
    end

    def adjust(w, s) end

    def getUIversion
    end

    def to_s
    end

  end
end
