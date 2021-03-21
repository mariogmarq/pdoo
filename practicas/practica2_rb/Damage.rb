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

        def self.newCopy(d)
            
        end

        def getNShields
            @nShields
        end

        def getNWeapons
            @nWeapons
        end

        def getWeapons
            @weapons
        end

        #Arreglarlo porque tiene que borrar todos los elementos del vector iguales a ese tipo, y con index solo borras el primero
        def discardWeapon(w)
            indice = getWeapons.index(w.type)
            if indice != nil
                getWeapons.delete_at(indice)
            else
                if getNWeapons>0
                    getNWeapons-=1
                else
                    getNWeapons=0
                end
            end
        end

        def discardShieldBooster
            if getNShields > 0
                getNShields-=1
            else
                getNShields = 0
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