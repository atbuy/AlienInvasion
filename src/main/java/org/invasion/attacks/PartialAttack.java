package org.invasion.attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.invasion.armies.AlienArmy;
import org.invasion.armies.Warrior;
import org.invasion.utils.Observable;
import org.invasion.utils.Observer;

public class PartialAttack implements AttackType, Observable {

  private AlienArmy army;
  private int soldiers;
  private List<Observer> observers = new ArrayList<Observer>();

  public int getAttackPower() {
    return this.soldiers;
  }

  public void setAttackPower(int soldiers) {
    this.soldiers = soldiers;
  }

  @Override
  public void setArmy(AlienArmy army) {
    this.army = army;
  }

  @Override
  public void decideAttackStrength(Scanner s) {
    int armySize = this.army.getArmySize();

    System.out.println("How many soldiers do you want to attack with?");

    int soldierAmount = 0;

    // Get soldiers to attack with, within allowed range
    while (soldierAmount < 1 || soldierAmount > armySize) {
      System.out.println(String.format("Select between 1 and %d soldiers.", armySize));
      System.out.print("Soldiers: ");

      try {
        soldierAmount = Integer.parseInt(s.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please input a number.");
      }
    }

    // Set attack power for this attack
    this.setAttackPower(soldierAmount);

    System.out.println(String.format("You will be attacking with %d soldiers.", this.getAttackPower()));
  }

  @Override
  public int attack(Warrior warrior) {
    return warrior.getHit(this.getAttackPower());
  }

  @Override
  public void attach(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void detach(Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : this.observers) {
      observer.update();
    }
  }

}
