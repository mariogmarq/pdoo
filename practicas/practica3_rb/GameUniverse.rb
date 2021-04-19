#encoding: utf-8

require_relative 'lib/GameState'

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
      @currentStation = nil
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
      state = @gameState.getState
      if state == GameState::CANNOTPLAY
        spaceStations = []
        dealer = CardDealer.instance

        names.each do |element|
          supplies = dealer.nextSuppliesPackage
          station = SpaceStation.new(element, supplies)
          spaceStations.add(station)
          nh = @dice.initWithNHangars
          nw = @dice.initWithNWeapons
          ns = @dice.initWithNShields

          lo = Loot.new(0,nw,ns,nh,0)
          station.setLoot(lo)
        end

        currentStationIndex = @dice.whoStarts(names.size)
        currentStation = spaceStations.get(currentStationIndex)
        currentEnemy = dealer.nextEnemy

        gameState.next(@turns, spaceStations.size)

      end
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
