#encoding: utf-8

require_relative 'HangarToUI'

module Deepspace

    class Hangar 
        
        #Constructor por defecto
        def initialize(capacity)
            @maxElements = capacity
            @weapons =[]
            @shieldBoosters = []
        end

        def to_s
            "Max Elements: " + @maxElements.to_s + "\nWeapons: " + @weapons.to_s + "\nShieldBoosters: " + @shieldBoosters.to_s + "\n"
        end
        #Consultores

        attr_reader :maxElements

        def weapons
            @weapons.clone
        end

        def shieldBoosters
            @shieldBoosters.clone
        end

        #Constructor de copia de la clase
        def self.newCopy(h)
            newh = Hangar.new(h.maxElements)
            h.weapons.each do |element|
                newh.addWeapon(element)
            end
            h.shieldBoosters.each do |element|
                newh.addShieldBooster(element)
            end
            return newh
        end

        #Metodos
        private def spaceAvailable
            if weapons.length + shieldBoosters.length < maxElements
                true
            else
                false
            end
        end

        def addWeapon(w)
            if spaceAvailable
                @weapons.push(w)
                true
            else
                false
            end
        end

        def addShieldBooster(s)
            if spaceAvailable
                @shieldBoosters.push(s)
                true
            else
                false
            end
        end
        
        def removeWeapon(w)
            if w>=0 && w<weapons.length
                aux = weapons[w]
                @weapons.delete_at(w)
                aux
            else
                nil
            end
        end

        def removeShieldBooster(s)
            if s>=0 && s<shieldBoosters.length
                aux = shieldBoosters[s]
                @shieldBoosters.delete_at(s)
                aux
            else
                nil
            end
        end

        def getUIversion
            HangarToUI.new(self)
        end
    end
end
