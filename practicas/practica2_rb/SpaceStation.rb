#encoding: utf-8

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

    def ammoPower
      @ammoPower
    end

    def fuelUnits
      @fuelUnits
    end

    def Hangar
      @hangar
    end

    def name
      @name
    end

    def nMedals
      @nMedals
    end

    def pendingDamage
      @pendingDamage
    end

    def shieldBoosters
      @shieldBoosters
    end

    def shieldPower
      @shieldPower
    end

    def speed
      @fuelUnits * 1.0 / @@MAXFUEL
    end

    def weapons
      @weapons
    end

    def fire
    end
    def protection
    end
    def reciveShot(shot)
    end
    def discardWeapon(i)
    end
    def discardShieldBooster(i)
    end
    def setLoot(l)
    end

    def discardHangar
      @hangar = nil
    end

    def discardShieldBoosterInHangar(i)
      if @hangar != nil
        @hangar.removeShieldBooster i
      end
    end

    def discardWeaponInHangar(i)
      if @hangar != nil
        @hangar.removeWeapon i
      end
    end

    def mountShieldBooster(i)
      if @hangar != nil
        aux = @hangar.removeShieldBooster i
        if aux != nil
          @shieldBoosters.push aux
        end
      end
    end

    def mountWeapon(i)
      if @hangar != nil
        aux = @hangar.removeWeapon i
        if aux != nil
          @weapons.push aux
        end
      end
    end

    def move
      @fuelUnits -= (@fuelUnits * speed).floor
      if @fuelUnits < 0
        @fuelUnits = 0
      end
    end

    def reciveHangar(h)
      if @hangar == nil
        @hangar = Hangar.newCopy h
      end
    end

    def receiveShieldBooster(s)
      if @hangar == nil
        return false
      end

      @hangar.addShieldBooster(s)
    end

    def receiveSupplies(s)
      @ammoPower += s.ammoPower
      @shieldPower += s.shieldPower
      assignFuelValue(@fuelUnits + s.fuelUnits)
    end

    def receiveWeapon(w)
      if @hangar == nil
        return false
      end

      @hangar.addWeapon(w)
    end

    def cleanUpMountedItems
      @weapons.delete_if { |weapon|  weapon.uses == 0}
      @shieldBoosters.delete_if { |s| s.uses == 0 }
    end

    def validState
      @pendingDamage == nil or @pendingDamage.hasNoEffect # Puede petar
    end

    def setPendingDamage(d)
      @pendingDamage = d.adjust(weapons, shieldBoosters)
    end

    private
    def assignFuelValue(f)
      @fuelUnits = f
      if @fuelUnits > @@MAXFUEL
        @fuelUnits = @@MAXFUEL
      end
    end

    def cleanPendingDamage
      if @pendingDamage != nil
        if @pendingDamage.hasNoEffect
          @pendingDamage = nil
        end
      end
    end


  end
end
