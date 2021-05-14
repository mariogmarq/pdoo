#encoding: utf-8

#Clase que representa a los potenciadores de escudo que pueden tener las estaciones espaciales

require_relative 'ShieldToUI'

module Deepspace

  class ShieldBooster

    #Constructor con parámetros de la clase
    def initialize(name, boost, uses)
      @name = name
      @boost = boost
      @uses = uses
    end

    #Constructor de copia de la clase
    def self.newCopy(orig)
      ShieldBooster.new(orig.name, orig.boost, orig.uses)
    end

    attr_reader :boost, :name, :uses

    #Método que decrementa uses en una unidad y devuelve boost si uses es mayor que 0,
    # o que devuelve 1.0 en otro caso
    def useIt
      if @uses > 0
        @uses -= 1
        @boost
      else
        1.0
      end
    end

    def to_s
      "Name:  "+ @name.to_s + "\nBoost: " + @boost.to_s + "\nUses: " + @uses.to_s
    end

    def getUIversion
      ShieldToUI.new self
    end

  end

end
