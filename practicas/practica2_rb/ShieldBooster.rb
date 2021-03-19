#encoding: utf-8

#Clase que representa a los potenciadores de escudo que pueden tener las estaciones espaciales

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

    #Método consultor de boost
    def boost
      @boost
    end

    #Metodo consultor del número de usos del potenciador de escudo 
    def uses
      @uses
    end

    #Método consultor del nombre del potenciador de escudo
    def name
      @name
    end

    #Método que decrementa uses en una unidad y devuelve boost si uses es mayor que 0,
    # o que devuelve 1.0 en otro caso
    def useIt
      if @uses > 0
        @uses -= 1
        return @boost
      else
        return 1.0
      end
    end

    def to_s
      return "Name:  "+ @name.to_s + "\nBoost: " + @boost.to_s + "\nUses: " + @uses.to_s
    end
    
  end

end
