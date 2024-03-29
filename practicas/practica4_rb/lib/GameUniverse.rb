#encoding: utf-8

require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'CombatResult'
require_relative 'ShotResult'
require_relative 'GameStateController'
require_relative 'Dice'
require_relative 'CardDealer'
require_relative 'SpaceStation'
require_relative 'GameUniverseToUI'
require_relative 'Transformation'

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
      @haveSpaceCity = false
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

    def discardShieldBooster(i)
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

    def state
      @gameState.state
    end

    def getUIversion
      GameUniverseToUI.new @currentStation, @currentEnemy
    end

    def init(names)
      statevar = state
      if statevar == GameState::CANNOTPLAY
        @spaceStations = []
        dealer = CardDealer.instance

        names.each do |element|
          supplies = dealer.nextSuppliesPackage
          station = SpaceStation.new(element, supplies)
          @spaceStations.push(station)
          nh = @dice.initWithNHangars
          nw = @dice.initWithNWeapons
          ns = @dice.initWithNShields

          lo = Loot.new(0,nw,ns,nh,0)
          station.setLoot(lo)
        end

        @currentStationIndex = @dice.whoStarts(names.size)
        @currentStation = @spaceStations.at(@currentStationIndex)
        @currentEnemy = dealer.nextEnemy

        @gameState.next(@turns, @spaceStations.size)

      end
    end

    def mountShieldBooster(i)
      @currentStation.mountShieldBooster i if valid_state
    end

    def mountWeapon(i)
      @currentStation.mountWeapon i if valid_state
    end

    def nextTurn
      statevar = @gameState.state

      if statevar == GameState::AFTERCOMBAT
        stationState = @currentStation.validState
        if stationState
          @currentStationIndex = (@currentStationIndex+1)%(@spaceStations.length)
          @turns+=1
          @currentStation = @spaceStations.at(@currentStationIndex)
          @currentStation.cleanUpMountedItems
          dealer = CardDealer.instance
          @currentEnemy = dealer.nextEnemy
          @gameState.next(@turns, @spaceStations.length)

          return true

        end
        return false
      end
      false
    end


    def combat
      statevar = state
      if statevar == GameState::BEFORECOMBAT || statevar == GameState::INIT
        combatResult = combatGo(@currentStation, @currentEnemy)
      else
        combatResult = CombatResult::NOCOMBAT
      end
      @gameState.next(@turns, @spaceStations.length)
      combatResult
        
    end

    def combatGo(station, enemy)
      ch = @dice.firstShot

        if ch == GameCharacter::ENEMYSTARSHIP
          fire = enemy.fire
          result = station.receiveShot(fire)

          if result == ShotResult::RESIST
            fire = station.fire
            result = enemy.receiveShot(fire)
            enemyWins=(result == ShotResult::RESIST)
          else
            enemyWins=true
          end

        else
          fire = station.fire
          result = enemy.receiveShot(fire)
          enemyWins = (result == ShotResult::RESIST)
        end

        if enemyWins
          s = station.speed
          moves = @dice.spaceStationMoves(s) 

          if !moves
            damage = enemy.damage
            station.setPendingDamage(damage)
            combatResult = CombatResult::ENEMYWINS
          else
            station.move
            combatResult = CombatResult::STATIONESCAPES
          end

        else
          aLoot = enemy.loot
          trans = station.setLoot(aLoot)
          combatResult = CombatResult::STATIONWINS

          if trans == Transformation::GETEFFICIENT
            makeStationEfficient
            return CombatResult::STATIONWINSANDCONVERTS
          elsif trans == Transformation::SPACECITY
            createSpaceCity
            return CombatResult::STATIONWINSANDCONVERTS
          end

        end
      combatResult
    end

    def makeStationEfficient
      if @dice.extraEfficiency
        @currentStation = BetaPowerEfficientSpaceStation.new(@currentStation)
      else
        @currentStation = PowerEfficientSpaceStation.new(@currentStation)
      end
        @spaceStations[@currentStationIndex] = @currentStation
    end

    def createSpaceCity
      if @haveSpaceCity == false
        stations = @spaceStations.clone
        stations.delete_at(@currentStationIndex)
        @currentStation = SpaceCity.new(@currentStation, stations)

        @spaceStations[@currentStationIndex] = @currentStation
      end
    end


  end

end
