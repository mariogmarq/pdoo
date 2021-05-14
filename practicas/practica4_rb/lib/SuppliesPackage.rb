#encoding: utf-8

#Clase que representa a un paquete de suministros para una estación espacial.

module Deepspace

  class SuppliesPackage

    #Constructor con parámetros de la clase
    def initialize(ammoPower, fuelUnits, shieldPower)
      @ammoPower = ammoPower
      @fuelUnits = fuelUnits
      @shieldPower = shieldPower
    end

    #Constructor de copia de la clase
    def self.newCopy(ins)
      SuppliesPackage.new(ins.ammoPower, ins.fuelUnits, ins.shieldPower)
    end

    #Método consultor del armamento
    def ammoPower
      @ammoPower
    end

    #Método consultor del combustible
    def fuelUnits
      @fuelUnits
    end

    #Método consultor de la energía para los escudos
    def shieldPower
      @shieldPower
    end

    def to_s
      "ammoPower: " + @ammoPower.to_s + "\nfuelUnits: " + @fuelUnits.to_s + "\nshieldPower: " + @shieldPower.to_s
    end

  end

end
