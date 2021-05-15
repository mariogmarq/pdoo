#encoding: utf-8

require_relative 'SpaceStation'
require_relative 'SpaceCityToUI'
require_relative 'ShotResult'
require_relative 'Transformation'

module Deepspace

  class SpaceCity < SpaceStation

    def initialize(base, rest)
      fromOther base
      @base = base
      @collaborators = rest
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
      super l
      Transformation::NOTRANSFORM
    end

    def getUIversion
      SpaceCityToUI.new self
    end

  end
end
