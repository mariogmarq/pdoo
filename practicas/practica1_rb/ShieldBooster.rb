#encoding: utf-8

module Deepspace

  class ShieldBooster

    def initialize(name, boost, uses)
      @name = name
      @boost = boost
      @uses = uses
    end

    def self.newCopy(orig)
      ShieldBooster.new(orig.name, orig.boost, orig.uses)
    end

    def boost
      @boost
    end

    def uses
      @uses
    end

    def name
      @name
    end

    def useIt
      if @uses > 0
        @uses -= 1
        return @boost
      else
        return 1.0
      end
    end
    
  end

end
