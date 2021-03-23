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

        #Constructor de copia
        def self.newCopy(e)
            newe = EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
            return newe
        end

        #Consultores
        attr_reader :name
        attr_reader :ammoPower
        attr_reader :shieldPower

        def loot
            Loot.newCopy(@loot)
        end

        def damage
            Damage.newCopy(@damage)
        end

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


    end
end
