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

        attr_reader :maxElements

	#Preguntar si deberia ser creando un array de NewCopys
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
        def spaceAvailable
            if weapons.length + shieldBoosters.length < maxElements
                return true
            else
                return false
            end
        end

        def addWeapon(w)
            if spaceAvailable
                @weapons.push(w)
                return true
            else
                return false
            end
        end

        def addShieldBooster(s)
            if spaceAvailable
                @shieldBoosters.push(s)
                return true
            else
                return false
            end
        end
        
        def removeWeapon(w)
            if w>=0 && w<weapons.length
                aux = weapons[w]
                @weapons.delete_at(w)
                return aux
            else
                return nil
            end
        end

        def removeShieldBooster(s)
            if s>=0 && s<shieldBoosters.length
                aux = shieldBoosters[s]
                @shieldBoosters.delete_at(s)
                return aux
            else
                return nil
            end
        end

        def getUIversion
            HangarToUI.new(this)
        end
    end
end
