#encoding: utf-8

#Clase que representa el daño producido a una estación espacial por una nave enemiga cuando se pierde un combate

#COMPLETAR

module Deepspace

    class Damage

        def initialize(nw,s, w)
            @nWeapons = nw
            @nShields = s 
            @weapons = w
        end

        def self.newNumericWeapons(w,s)
            new(w,s,nil)
        end

        def self.newSpecificWeapons(w,s)
            new(nil,s,w)
        end

        #Constructor de copia
        def self.newCopy(d)
            
        end

        #Consultores
        
        attr_reader :nShields

        attr_reader :nWeapons

        def weapons
            @weapons.clone
        end

        #Arreglarlo porque tiene que borrar todos los elementos del vector iguales a ese tipo, y con index solo borras el primero
        def discardWeapon(w)
            indice = weapons.index(w.type)
            if indice != nil
                @weapons.delete_at(indice)
            else
                if nWeapons>0
                    @nWeapons-=1
                else
                    @nWeapons=0
                end
            end
        end

        def discardShieldBooster
            if nShields > 0
                @nShields-=1
            else
                @nShields = 0
            end
        end

        def hasNoEffect
        end

        private def arrayContainsType(w,t)
            indice = w.index(t)
            if(indice != nill)
                return indice
            else
                return -1
            end
        end

        def adjust(w,s)

        end


    end
end