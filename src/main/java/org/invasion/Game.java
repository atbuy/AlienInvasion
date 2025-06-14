package org.invasion;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.invasion.armies.AlienArmy;
import org.invasion.armies.Warrior;
import org.invasion.attacks.AttackType;
import org.invasion.attacks.FullAttack;
import org.invasion.attacks.PartialAttack;

public class Game {
  private Warrior warrior;
  private AlienArmy alienArmy;

  public void showIntroduction() {
    System.out.println("Welcome to ArmyInvasion! :)");
    System.out
        .println("You are playing as an alien army that needs to strategically attack an evil warrior and beat them.");
  }

  public void mainLoop(Scanner scanner) {
    // Initialize attack options and configure army
    PartialAttack partialAttack = new PartialAttack();
    FullAttack fullAttack = new FullAttack();

    partialAttack.setArmy(this.alienArmy);
    fullAttack.setArmy(this.alienArmy);

    // Structure attacks to map option number to attack type
    Map<Integer, AttackType> attacks = new HashMap<Integer, AttackType>() {
      {
        put(1, partialAttack);
        put(2, fullAttack);
      }
    };

    // Start main game loop
    while (true) {
      // Select an attack type
      int attackOption = this.getArmyAttackType(scanner);
      AttackType attackType = attacks.get(attackOption);
      System.out.println(String.format("Attack type selected: %s", attackType));

      // Update warrior's visibility tool after selecting attack type
      this.warrior.decideVisibility(attackOption);

      // Attach visibility tool to the selected attack type
      if (attackOption == 1) {
        ((PartialAttack) attackType).attach(this.warrior.getVisibility());
      } else if (attackOption == 2) {
        ((FullAttack) attackType).attach(this.warrior.getVisibility());
      }

      // Decide attack strength:
      // PartialAttack: Takes input from stdin to find how many soldiers will attack.
      // FullAttack: Uses the total army strength to attack the warrior.
      attackType.decideAttackStrength(scanner);

      // The warrior is attacked. This also updates the warrior's visibility tool
      int newPower = attackType.attack(this.warrior);
      this.warrior.setPower(newPower);

      // Show state after both turns
      this.showState();

      // Check if the army lost
      if (this.alienArmy.getArmySize() <= 0) {
        this.showDefeat();
        break;
      }

      // Check if the warrior lost
      if (this.warrior.getPower() <= 0) {
        this.showVictory();
        break;
      }


      // Detach visibility tool from the selected attack type
      if (attackOption == 1) {
        ((PartialAttack) attackType).detach(this.warrior.getVisibility());
      } else if (attackOption == 2) {
        ((FullAttack) attackType).detach(this.warrior.getVisibility());
      }
    }
  }

  public void showVictory() {
    System.out.println("You won!");
  }

  public void showDefeat() {
    System.out.println("You lost :(");
  }

  public void initializeWarrior(Scanner s) {
    this.warrior = new Warrior();
    this.warrior.setPower(this.getWarriorStrength(s));
  }

  public void initializeAlienArmy() {
    this.alienArmy = new AlienArmy();
    this.alienArmy.setArmySize(this.getAlienArmySize());
  }

  public void setAlienArmy(AlienArmy alienArmy) {
    this.alienArmy = alienArmy;
  }

  public int getWarriorStrength(Scanner s) {

    // Map strength options to actual values
    Map<Integer, Integer> strengthMap = new HashMap<Integer, Integer>() {
      {
        put(1, 100);
        put(2, 200);
        put(3, 300);
      }
    };

    // Continuously ask for warrior strength, until given an option
    boolean scanned = false;
    int strength = 2;

    while (!scanned) {

      System.out.println("Please select how strong you want the warrior to be:");
      System.out.println("1) Weak");
      System.out.println("2) Normal");
      System.out.println("3) Strong");
      System.out.print("Strength: ");

      String scannedStrength = s.nextLine();

      try {
        strength = Integer.parseInt(scannedStrength);

        if (strength < 1 || strength > 3) {
          scanned = false;
          System.out.println("Please input a number between 1 and 3.");
          continue;
        }

        scanned = true;
      } catch (NumberFormatException e) {
        System.out.println("You did not input a number.");
      }
    }

    // Get a random value to add/subtract from the strength
    int strengthNoise = new Random().nextInt(-20, 20);
    int finalStrength = strengthMap.get(strength) + strengthNoise;

    return finalStrength;
  }

  public int getAlienArmySize() {
    // The alien army always has 85% of the warrior's power
    int warriorPower = this.warrior.getPower();

    return (int) (warriorPower * 0.85);
  }

  public int getArmyAttackType(Scanner s) {

    System.out.println("Select an attack type.");

    boolean scanned = false;
    int attackType = 0;

    while (!scanned) {
      System.out.println("1) Partial Attack");
      System.out.println("2) Full Attack");
      System.out.println("When using Partial Attack, the warrior will be able to attack back 50% of your soldiers.");
      System.out.println("When using Full Attack, the warrior will be able to attack back all of your soldiers.");
      System.out.print("Select attack type: ");

      String scannedAttack = s.nextLine();

      try {
        attackType = Integer.parseInt(scannedAttack);

        if (attackType < 1 || attackType > 2) {
          scanned = false;
          System.out.println("Please input a either (1) or (2).");
          continue;
        }

        scanned = true;
      } catch (NumberFormatException e) {
        System.out.println("You did not input a number.");
      }
    }

    return attackType;
  }

  public int getPartialAttackSoldiers(Scanner s) {
    System.out.print("How many soldiers do you want to attack with?: ");

    int soldiers = -1;

    while (soldiers < 0) {
      try {
        soldiers = Integer.parseInt(s.nextLine());

        if (soldiers > this.alienArmy.getArmySize()) {
          System.out.println(String.format("You can't select more than %d soldiers.", this.alienArmy.getArmySize()));

        }
      } catch (NumberFormatException e) {
        System.out.println("You did not input a number.");
      }
    }

    return soldiers;
  }

  public void showState() {
    System.out.println(String.format("-- Warrior strength: %d power", this.warrior.getPower()));
    System.out.println(String.format("-- Alien army size: %d soldiers", this.alienArmy.getArmySize()));
  }
}
