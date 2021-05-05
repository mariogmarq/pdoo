#encoding: utf-8
require_relative 'Damage'
require_relative 'Weapon'
require_relative 'WeaponType'

class TestDamage
  def initialize (w, expected)
    @w = w
    @esperado = expected
  end

  def test(damage)
    damage.discardWeapon(@w)
    if @esperado.nWeapons == damage.nWeapons
    else
      puts "ERROR"
    end
  end

  def self.test
    damage = Deepspace::Damage.newNumericWeapons(3, 4)
    casos = [new(Deepspace::Weapon.new("BATAMANTA", Deepspace::WeaponType::MISSILE, 2),Deepspace::Damage.newNumericWeapons(2,4))]
    casos.each do |element|
    damage2 = Deepspace::Damage.newCopy(damage)
      element.test(damage2)
    end
  end
end

TestDamage.test