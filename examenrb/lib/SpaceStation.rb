#encoding: utf-8

require_relative 'CardDealer'
require_relative 'SpaceStationToUI'

module Deepspace
  class SpaceStation
    @@MAXFUEL = 100
    @@SHIELDLOSTPERUNITSHOT = 0.1

    def initialize(n, supplies)
      @name = n
      @ammoPower = supplies.ammoPower
      @shieldPower = supplies.shieldPower
      @nMedals = 0
      @fuelUnits = supplies.fuelUnits
      @pendingDamage = nil
      @weapons = []
      @shieldBoosters = []
      @hangar = nil

    end

    def getUIversion
      SpaceStationToUI.new self
    end
    
    attr_reader :fuelUnits, :ammoPower, :nMedals, :name, :shieldPower

    def hangar
      return Hangar.newCopy @hangar unless @hangar.nil?

      nil
    end

    def pendingDamage
      return Damage.newCopy @pendingDamage if !@pendingDamage.nil?

      nil
    end

    def shieldBoosters
      @shieldBoosters.clone
    end

    def speed
      @fuelUnits * 1.0 / @@MAXFUEL
    end

    def weapons
      @weapons.clone
    end

    def fire
      factor = 1.0
      @weapons.each { |w|
        factor *= w.useIt
      }
      factor * @ammoPower
    end

    def protection
      factor = 1.0
      @shieldBoosters.each { |s|
        factor *= s.useIt
      }
      factor * @shieldPower
    end

    def receiveShot(shot)
      myProtection = protection

      if myProtection >= shot
        @shieldPower -= @@SHIELDLOSTPERUNITSHOT*shot
        @shieldPower = [@shieldPower, 0.0].max
        ShotResult::RESIST
      else
        @shieldPower = 0.0
        ShotResult::DONOTRESIST
      end
    end

    def discardWeapon(i)
      size = @weapons.size
      if i >= 0 and i < size
        w = @weapons.at i
        @weapons.delete_at i

        if @pendingDamage != nil
          @pendingDamage.discardWeapon w
          cleanPendingDamage
        end
      end
    end

    def discardShieldBooster(i)
      size = @shieldBoosters.size
      if i >= 0 and i < size
        s = @shieldBoosters.at i
        @shieldBoosters.delete_at i

        if @pendingDamage != nil
          @pendingDamage.discardShieldBooster
          cleanPendingDamage
        end
      end
    end

    def setLoot(l)
      dealer = CardDealer.instance
      h = l.nHangars

      if h > 0
        hangar = dealer.nextHangar
        receiveHangar hangar
      end

      elements = l.nSupplies

      elements.times do
        sup = dealer.nextSuppliesPackage
        receiveSupplies sup
      end

      elements = l.nWeapons

      elements.times do
        w = dealer.nextWeapon
        receiveWeapon w
      end

      elements = l.nShields

      elements.times do
        s = dealer.nextShieldBooster
        receiveShieldBooster s
      end

      @nMedals += l.nMedals
    end

    def discardHangar
      @hangar = nil
    end

    def discardShieldBoosterInHangar(i)
      @hangar.removeShieldBooster i if !@hangar.nil?
    end

    def discardWeaponInHangar(i)
      @hangar.removeWeapon i if !@hangar.nil?
    end

    def mountShieldBooster(i)
      if !@hangar.nil?
        aux = @hangar.removeShieldBooster i
        @shieldBoosters.push aux if !aux.nil?
      end
    end

    def mountWeapon(i)
      if !@hangar.nil?
        aux = @hangar.removeWeapon i
        @weapons.push aux if !aux.nil?
      end
    end

    def move
      @fuelUnits -= (@fuelUnits * speed).floor
      @fuelUnits = 0 if @fuelUnits.negative?
    end

    def receiveHangar(h)
      @hangar = Hangar.newCopy h if @hangar.nil?
    end

    def receiveShieldBooster(s)
      return false if @hangar.nil?

      @hangar.addShieldBooster(s)
    end

    def receiveSupplies(s)
      @ammoPower += s.ammoPower
      @shieldPower += s.shieldPower
      assignFuelValue(@fuelUnits + s.fuelUnits)
    end

    def receiveWeapon(w)
      return false if @hangar.nil?

      @hangar.addWeapon(w)
    end

    def cleanUpMountedItems
      @weapons.delete_if { |weapon|  weapon.uses.zero?}
      @shieldBoosters.delete_if { |shield| shield.uses.zero? }
    end

    def validState
      @pendingDamage.nil? or @pendingDamage.hasNoEffect # Puede petar
    end

    def setPendingDamage(d)
      @pendingDamage = d.adjust(weapons, shieldBoosters)
    end

    private
    def assignFuelValue(f)
      @fuelUnits = f
      @fuelUnits = @@MAXFUEL if @fuelUnits > @@MAXFUEL
    end

    def cleanPendingDamage
      if !@pendingDamage.nil?
        @pendingDamage = nil if @pendingDamage.hasNoEffect
      end
    end


  end
end
