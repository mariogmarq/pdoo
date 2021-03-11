#encoding: utf-8
require_relative "GameCharacter"

module Deepspace
  
  class Dice
    
    def initialize
      @NHANGARSPROB = 0.25
      @NSHIELDSPROB = 0.25
      @NWEAPONSPROB = 0.33
      @FIRSTSHOTPROB = 0.5
      @generator = Random.new
    end

    def initWithNHangars
      if @generator.rand 1.0 < @NHANGARSPROB
        return 0
      else 
        return 1
      end
    end

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

    def initWithNShields
      if @generator.rand(1.0) < @NSHIELDSPROB
        return 0
      else
        return 1
      end
    end

    def whoStarts(nPlayers)
      @generator.rand(nPlayers)
    end

    def firstShot
      if @generator.rand(1.0) < @FIRSTSHOTPROB
        return GameCharacter::SPACESTATION
      else
        return GameCharacter::ENEMYSTARSHIP
      end
    end

    def spaceStationMoves(speed)
      @generator.rand 1.0 < speed
    end

  end

end
