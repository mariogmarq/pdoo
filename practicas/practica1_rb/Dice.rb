#encoding: utf-8
require_relative "GameCharacter"

#Clase que tiene la responsabilidad de tomar todas las decisiones que dependen del azar en el juego.

module Deepspace
  
  class Dice
    
    #Constructor sin parámetros de la clase
    def initialize
      @NHANGARSPROB = 0.25
      @NSHIELDSPROB = 0.25
      @NWEAPONSPROB = 0.33
      @FIRSTSHOTPROB = 0.5
      @generator = Random.new
    end

    #Método que determina el número de hangares que recibirá una estación espacial al ser creada
    def initWithNHangars
      if @generator.rand 1.0 < @NHANGARSPROB
        return 0
      else 
        return 1
      end
    end

    #Método que determina el número de armas que recibirá una estación espacial al ser creada
    def initWithNWeapons
      guess = @generator.rand(1.0)
      if guess < @NWEAPONSPROB
        return 1
      elsif guess < 2 * @NWEAPONSPROB
        return 2
      else
        return 3
      end
    end

    #Método que determina el número de potenciadores de escudo que recibirá una estación espacial al ser creada
    def initWithNShields
      if @generator.rand(1.0) < @NSHIELDSPROB
        return 0
      else
        return 1
      end
    end

    #Método que determina el jugador(su índice) que iniciará la partida
    def whoStarts(nPlayers)
      @generator.rand(nPlayers)
    end

    #Método que determina quién dispara primero en un combate: la estación espacial o la nave enemiga
    def firstShot
      if @generator.rand(1.0) < @FIRSTSHOTPROB
        return GameCharacter::SPACESTATION
      else
        return GameCharacter::ENEMYSTARSHIP
      end
    end

    #Método que determina si la estación espacial se moverá para esquivar un disparo.
    def spaceStationMoves(speed)
      @generator.rand 1.0 < speed
    end

  end

end
