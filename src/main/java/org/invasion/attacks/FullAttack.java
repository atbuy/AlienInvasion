package org.invasion.attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.invasion.armies.AlienArmy;
import org.invasion.armies.Warrior;
import org.invasion.utils.Observer;
import org.invasion.utils.Observable;

public class FullAttack implements AttackType, Observable {

  List<Observer> observers = new ArrayList<Observer>();
  private AlienArmy army;
  private int soldiers;

  @Override
  public void setArmy(AlienArmy army) {
    this.army = army;
  }

  @Override
  public void decideAttackStrength(Scanner s) {
    this.soldiers = this.army.getArmySize();
    System.out.println(String.format("You will be attacking will all of your %d soldiers.", this.soldiers));
  }

  @Override
  public int attack(Warrior warrior) {
    return warrior.getHit(this.soldiers);
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
