#encoding: utf-8

require_relative 'ShotResult'

module Deepspace

    class EnemyStarShip

        #Constructor con parametros
        def initialize(n, a, s, l, d)
            @name = n
            @ammoPower = a
            @shieldPower = s
            @loot = l
            @damage = d
        end

        def to_s
            "Name: " + @name + "\nammoPower: " + @ammoPower.to_s + "\nshieldPower: " + @shieldPower.to_s + "\nloot: " + @loot.to_s + "\ndamage: " + @damage.to_s + "\n"
        end
        #Constructor de copia
        def self.newCopy(e)
            newe = EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
            return newe
        end

        #Consultores
        attr_reader :name
        attr_reader :ammoPower
        attr_reader :shieldPower
        attr_reader :loot
        attr_reader :damage

        def protection
            @shieldPower
        end

        def fire
            @ammoPower
        end

        def receiveShot(shot)
            if shieldPower < shot
                return ShotResult::DONOTRESIST
            else
                return ShotResult::RESIST
            end
        end

        def getUIversion()
            EnemyToUI.new(self)
        end


    end
end
