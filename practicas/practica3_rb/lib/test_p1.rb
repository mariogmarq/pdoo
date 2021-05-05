#encoding: utf-8

require_relative 'CombatResult'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'Loot'
require_relative 'ShieldBooster'
require_relative 'ShotResult'
require_relative 'SuppliesPackage'
require_relative 'Weapon'
require_relative 'WeaponType'

class Test_p1
  def testLoot
    puts 'Test Loot'
    loot1 = Deepspace::Loot.new(1, 2, 3, 4, 5)
    puts "1=#{loot1.nSupplies}"
    puts "2=#{loot1.nWeapons}"
    puts "3=#{loot1.nShields}"
    puts "4=#{loot1.nHangars}"
    puts "5=#{loot1.nMedals}"

    puts "\n"
  end

  def testShieldBoost
    puts 'Test ShieldBoost'
    s1 = Deepspace::ShieldBooster.new('s1', 2.0, 2)
    puts "s1=#{s1.name}"
    puts "2.0=#{s1.boost}"
    puts "2=#{s1.uses}"

    puts "2.0=#{s1.useIt}"
    s1.useIt.to_s
    puts "1.0=#{s1.useIt}"

    copy = Deepspace::ShieldBooster.newCopy(s1)
    puts "#{s1.name}=#{copy.name}"
    puts "#{s1.boost}=#{copy.boost}"
    puts "#{s1.uses}=#{copy.uses}"

    puts "\n"
  end

  def testSuppliesPackage
    puts 'Test SuppliesPackage'

    s1 = Deepspace::SuppliesPackage.new(1.0, 2.0, 3.0)
    puts "1.0=#{s1.ammoPower}"
    puts "2.0=#{s1.fuelUnits}"
    puts "3.0=#{s1.shieldPower}"
    copy = Deepspace::SuppliesPackage.newCopy(s1)
    puts "#{s1.ammoPower}=#{copy.ammoPower}"
    puts "#{s1.fuelUnits}=#{copy.fuelUnits}"
    puts "#{s1.shieldPower}=#{copy.shieldPower}"

    puts "\n"
  end

  def testWeapon
    puts 'Test Weapon'

    w1 = Deepspace::Weapon.new("w1", Deepspace::WeaponType::LASER, 1)
    puts "w1=#{w1.name}"
    puts "2.0=#{w1.power}"
    puts "1=#{w1.uses}"
    puts "2.0=#{w1.useIt}"
    puts "1.0=#{w1.useIt}"

    copy = Deepspace::Weapon.newCopy(w1)
    puts "#{w1.name}=#{copy.name}"
    puts "#{w1.power}=#{copy.power}"
    puts "#{w1.uses}=#{copy.uses}"
    puts "\n"
  end

  def testDice
    puts 'Test Dice'
    dice = Deepspace::Dice.new
    testArray = [0, 0]

    puts 'Test hangars prob: 0.25'
    100.times {
      testArray[dice.initWithNHangars] += 1
    }
    puts "0:#{testArray[0]} 1:#{testArray[1]}"

    puts 'Test Weapons prob: 0.33'
    testArray = [0, 0, 0]

    100.times {
      testArray[dice.initWithNWeapons - 1] += 1
    }
    puts "1:#{testArray[0]} 2:#{testArray[1]} 3:#{testArray[2]}"

    puts 'Test shields prob: 0.25'
    testArray = [0, 0]
    100.times {
      testArray[dice.initWithNShields] += 1
    }
    puts "0:#{testArray[0]} 1:#{testArray[1]}"

    puts 'Test whoStarts: 5 players'
    testArray = [0, 0, 0, 0, 0]
    100.times {
      testArray[dice.whoStarts(5)] += 1
    }
    puts "0:#{testArray[0]} 1:#{testArray[1]} 2:#{testArray[2]} 3:#{testArray[3]} 4:#{testArray[4]}"

    puts 'Test firstShot prob: 0.5'
    testArray = [0, 0]
    100.times {
      result = dice.firstShot
      index = 0

      if result == Deepspace::GameCharacter::ENEMYSTARSHIP
        index = 1
      end

      testArray[index] += 1
    }
    puts "SPACESTATION:#{testArray[0]} ENEMYSTARSHIP:#{testArray[1]}"

    testArray = [0, 0]

    puts 'Test moves prob: 0.5'
    100.times {
      index = 0
      if dice.spaceStationMoves 0.5
        index = 1
      end
      testArray[index] += 1
    }
    puts "false:#{testArray[0]} true:#{testArray[1]}"

    puts "\n"
  end

  def main
    testLoot
    testShieldBoost
    testSuppliesPackage
    testWeapon
    testDice
  end

  main
end