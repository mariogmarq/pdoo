#encoding: utf-8

require_relative 'Dice'
require_relative 'PowerEfficientSpaceStation'
require_relative 'BetaPowerEfficientSpaceStationToUI'

module Deepspace
  class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
    @@EXTRAEFFICIENCYFACTOR = 1.2

    def fire
      (Dice.new.extraEfficiency) ? super*@@EXTRAEFFICIENCYFACTOR : super
    end

    def getUIversion
      BetaPowerEfficientSpaceStationToUI.new self
    end
  end
end
