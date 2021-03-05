#encoding: utf-8

module Deepspace

  class Weapon

    def initialize(name, type, uses)
      @name = name
      @type = type
      @uses = uses
    end

    def self.newCopy(orig)
      Weapon.new(orig.name, orig.type, orig.uses)
    end

    def name
      @name
    end

    def type
      @type
    end

    def uses
      @uses
    end

    def power
     @type.power
    end

    def useIt
      if @uses > 0
        @uses -= 1
        return power
      else
        return 1.0
      end
    end

  end

end
