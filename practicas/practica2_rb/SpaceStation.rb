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
    
    attr_reader :fuelUnits, :ammoPower, :nMedals, :name, :shieldPower

    def Hangar
      return Hangar.newCopy @hangar unless @hangar.nil?

      nil
    end

    def pendingDamage
      return Damage.newCopy @pendingDamage if !pendingDamage.nil?

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

    def fire; end
    def protection; end
    def reciveShot(shot); end
    def discardWeapon(i); end
    def discardShieldBooster(i); end
    def setLoot(l); end

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

    def reciveHangar(h)
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
      @shieldBoosters.delete_if { |s| s.uses.zero? }
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
