#encoding: utf-8

require_relative 'Dice'

module Deepspace
  class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
    @@EXTRAEFFICIENCYFACTOR = 1.2

    def initialize(station)
      super
    end

    def fire
      (Dice.new.extraEfficiency) ? super*@@EXTRAEFFICIENCYFACTOR : super
    end
  end
end
