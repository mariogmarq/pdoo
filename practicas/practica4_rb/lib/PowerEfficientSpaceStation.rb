#encoding: utf-8
require_relative 'SpaceStation'
require_relative 'PowerEfficientSpaceStationToUI'

module Deepspace
  class PowerEfficientSpaceStation < SpaceStation
    @@EFFICIENCYFACTOR = 1.10

    def initialize(station)
      fromOther(station)
    end

    def fire
      super*@@EFFICIENCYFACTOR
    end

    def protection
      super*@@EFFICIENCYFACTOR
    end

    def setLoot(l)
      res = super(l)
      if res == Transformation::SPACECITY
        res = Transformation::NOTRANSFORM
      end
    end

    def getUIversion
      PowerEfficientSpaceStationToUI.new self
    end

  end
end