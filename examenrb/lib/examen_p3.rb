#encoding: utf-8

require_relative 'Damage'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'ShieldBooster'

module P3

    class ExamenP3

        def self.principal
            array = [Deepspace::WeaponType::LASER,Deepspace::WeaponType::LASER, Deepspace::WeaponType::PLASMA]
            daño1 = Deepspace::Damage.newNumericWeapons(2,3)
            daño2 = Deepspace::Damage.newSpecificWeapons(array, 0)

            array2w = [Deepspace::Weapon.new("Arma1", Deepspace::WeaponType::PLASMA, 2), Deepspace::Weapon.new("Arma2", Deepspace::WeaponType::PLASMA, 2), Deepspace::Weapon.new("Arma4", Deepspace::WeaponType::LASER, 2), Deepspace::Weapon.new("Arma5", Deepspace::WeaponType::LASER, 2), Deepspace::Weapon.new("Arma6", Deepspace::WeaponType::LASER, 2)]
            array2s = [Deepspace::ShieldBooster.new("pot1", 2 , 3) , Deepspace::ShieldBooster.new("pot2", 4 , 1) ]
            array3w = [Deepspace::Weapon.new("Arma1", Deepspace::WeaponType::PLASMA, 2), Deepspace::Weapon.new("Arma1", Deepspace::WeaponType::PLASMA, 2),Deepspace::Weapon.new("Arma4", Deepspace::WeaponType::LASER, 2),Deepspace::Weapon.new("Arma4", Deepspace::WeaponType::LASER, 2),Deepspace::Weapon.new("Arma4", Deepspace::WeaponType::MISSILE, 2),Deepspace::Weapon.new("Arma4", Deepspace::WeaponType::MISSILE, 2)]
            array3s = []
            d1ajustado = daño1.adjust(array2w,array2s )
            d2ajustado = daño2.adjust(array3w, array3s)
            
            puts d1ajustado.inspect
            puts d2ajustado.inspect
            puts daño2.inspect
        end

    end
end

P3::ExamenP3.principal
