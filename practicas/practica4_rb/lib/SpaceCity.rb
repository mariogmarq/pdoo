#encoding: utf-8

require_relative 'SpaceStation'

module Deepspace

  class SpaceCity < SpaceStation
    def initialize(base, rest)
      @base = base
      @collaborators = rest.clone
    end

    def collaborators
      @collaborators.clone
    end

    def protection
      protection_power = 0
      protection_power += @base.protection

      @collaborators.each do |c|
        protection_power += c.protection
      end
      protection_power
    end

    def fire
      fire_power = 0
      fire_power += @base.fire

      @collaborators.each do |c|
        fire_power += c.fire
      end

      fire_power
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

      if l.spaceCity
        Transformation::SPACECITY
      elsif l.getEfficient
        Transformation::GETEFFICIENT
      else
        Transformation::NOTRANSFORM
      end
    end

  end
end
