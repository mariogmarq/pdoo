#encoding: utf-8

module Deepspace

  class SuppliesPackage

    def initialize(ammoPower, fuelUnits, shieldPower)
      @ammoPower = ammoPower
      @fuelUnits = fuelUnits
      @shieldPower = shieldPower
    end

    def self.class.newCopy(ins)
      SuppliesPackage.new(ins.ammoPower, ins.fuelUnits, ins.shieldPower)
    end

    def ammoPower
      @ammoPower
    end

    def fuelUnits
      @fuelUnits
    end

    def shieldPower
      @shieldPower
    end

  end

end
