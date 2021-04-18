#encoding: utf-8


#Clase que representa el daño producido a una estación espacial por una nave enemiga cuando se pierde un combate


module Deepspace

  class Damage

    NO_USE = -1

    private def initialize(nw, s, w)
      @nWeapons = nw
      @nShields = s
      @weapons = w
    end

    def self.newNumericWeapons(w, s)
      new(w, s, nil)
    end

    def self.newSpecificWeapons(w, s)
      new(NO_USE, s, w)
    end

      #Constructor de copia
    def self.newCopy(d)
      if(d.weapons!=nil)
        newd = Damage.newSpecificWeapons(d.weapons, d.nShields)
      else
        newd = Damage.newNumericWeapons(d.nWeapons, d.nShields)
      end
        return newd
    end

      #Consultores

    attr_reader :nShields

    attr_reader :nWeapons

    attr_reader :weapons

      #Alternativa 1 (solo borra el primero)
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

      #Alternativa 2 (borra todos)
    def discardWeapon(w)
      if weapons != nil
        @weapons.delete_if { |element| element == w.type }
      else
        if nWeapons > 0
          @nWeapons -= 1
        else
          @nWeapons = 0
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
      if weapons != nil
        if !weapons.empty
          return false
        end
      else
        if nWeapons > 0
          return false
        end
      end
        if nShields > 0
          return false
        end
        return true
    end

      private def arrayContainsType(w, t)
        indice = w.index(t)
          if(indice != nil)
            return indice
          else
            return -1
          end
      end



    def adjust(w, s)
      limit_nshields = [s.length, nShields].min

        if weapons==nil
          limit_nweapons = [w.length, nWeapons].min
            return Damage.newNumericWeapons(limit_nweapons, limit_nshields)

        else
          result = []
            w_aux = w.clone
            weapons.each do |element|
              indice = arrayContainsType(w_aux, element)
                

                if indice != -1
                  result.push(element)
                    w_aux.delete_at(indice)
                end
            end

            Damage.newSpecificWeapons(result, limit_nshields)

        end
    end



    def getUIversion
      DamageToUI.new(self)
    end

    def to_s
      if @weapons.nil?
        "nWeapons: " + @nWeapons.to_s + "\nnShields: " + @nShields.to_s + "\n"
      else
        "weapons: " + @weapons.to_s + "\nnShields: " + @nShields.to_s + "\n"
      end
    end

  end
end
