#encoding: utf-8

module Deepspace

    class Hangar 
        
        #Constructor por defecto
        def initialize(capacity)
            @maxElements = capacity
            @weapons =[]
            @shieldBoosters = []
        end

        #Consultores
        def getMaxElements
            @maxElements
        end

        def getWeapons
            @weapons
        end

        def getShieldBoosters
            @shieldBoosters
        end

        #Constructor de copia de la clase
        def self.newCopy(h)
            newh = Hangar.new(h.maxElements)
            h.getWeapons.each do |element|
                newh.addWeapon(element)
            end
            h.getShieldBoosters.each do |element|
                newh.addShieldBooster(element)
            end
            return newh
        end

        
        def spaceAvailable
            if getWeapons.length + getShieldBoosters.length < @maxElements
                return true
            else
                return false
            end
        end

        def addWeapon(w)
            if spaceAvailable
                getWeapons.push(w)
                return true
            else
                return false
            end
        end

        def addShieldBooster(s)
            if spaceAvailable
                getShieldBoosters.push(s)
                return true
            else
                return false
            end
        end
        
        def removeWeapon(w)
            if w>=0 && w<getWeapons.length
                aux = getWeapons[w]
                .delete_at(w)
                return aux
            else
                return nil
            end
        end

        def removeShieldBooster(s)
            if s>=0 && s<getShieldBoosters.length
                aux = getShieldBoosters[s]
                getShieldBoosters.delete_at(s)
                return aux
            else
                return nil
            end
        end
    end
end