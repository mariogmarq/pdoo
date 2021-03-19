#encoding: utf-8

#Enumerado que representa a los tipos de armas del juego.
#Cada valor posible del tipo de enumerado tendrá un valor numérico concreto
#igual a la potencia de disparo de cada tipo de armas

module Deepspace
  module WeaponType

    #Clase auxiliar para representar el enumerado
    class Type

      #Constructor con parametros de la clase Type
      def initialize(pwr)
        @power = pwr
      end

      #Metodo consultor del atributo power
      def power
        @power
      end
    end

    LASER = Type.new 2.0
    MISSILE = Type.new 3.0
    PLASMA = Type.new 4.0

  end
end
