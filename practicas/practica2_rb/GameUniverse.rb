#encoding: utf-8

module Deepspace

  class GameUniverse
    @@WIN = 10

    def initialize
      @currentStationIndex = 0
      @turns = 0
      @gameState = GameStateController.new
      @dice = Dice.new
      @currentEnemy = nil
      @spaceStations = []
      @currentStation = SpaceStation.new
    end

    private def valid_state
      @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
    end

    def haveAWinner
      @currentStation.nMedals == @@WIN
    end

    def discardHangar
        @currentStation.discardHangar if valid_state
    end

    def discardShielBooster(i)
      @currentStation.discardShieldBooster i if valid_state
    end

    def discardShieldBoosterInHangar(i)
      @currentStation.discardShieldBoosterInHangar i if valid_state
    end

    def discardWeapon(i)
      @currentStation.discardWeapon i if valid_state
    end

    def discardWeaponInHangar(i)
      @currentStation.discardWeaponInHangar i if valid_state
    end

    def getState
      @gameState.state
    end

    def getUIversion
      GameUniverseToUI.new @currentStation, @currentEnemy
    end

    def init(names)
    end

    def mountShieldBooster(i)
      @currentStation.mountShieldBooster i if valid_state
    end

    def mountWeapon(i)
      @currentStation.mountWeapon i if valid_state
    end

    def nextTurn
    end

    def combat
    end

    def combatGo(station, enemy)
    end


  end

end
