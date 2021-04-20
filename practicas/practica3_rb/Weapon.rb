#encoding: utf-8

#Clase que representa las armas de las que puede disponer una estación espacial para potenciar su energía al disparar

require_relative 'lib/WeaponToUI'

module Deepspace

  class Weapon

    #Constructor sin parámetros de la clase
    def initialize(name, type, uses)
      @name = name
      @type = type
      @uses = uses
    end

    #Constructor de copia de la clase
    def self.newCopy(orig)
      Weapon.new(orig.name, orig.type, orig.uses)
    end

    #Método consultor de nombre del arma
    def name
      @name
    end

    #Método consultor del tipo de arma
    def type
      @type
    end

    #Método consultor del número de usos del arma
    def uses
      @uses
    end

    #Método que devuelve la potencia de disparo del arma
    def power
     @type.power
    end


    #Método que decrementa uses en una unidad y devuelve el valor de power si uses es mayor que 0,
    #o que devuelve 1.0 en otro caso
    def useIt
      if @uses > 0
        @uses -= 1
        return power
      else
        return 1.0
      end
    end

    def to_s
      return "Name: " + @name.to_s + "\nType: " + @type.to_s + "\nUses: " + @uses.to_s
    end

    def getUIversion
      WeaponToUI.new self
    end

  end

end