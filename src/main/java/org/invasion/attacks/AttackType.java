package org.invasion.attacks;

import org.invasion.armies.Warrior;

import java.util.Scanner;

import org.invasion.armies.AlienArmy;

public interface AttackType {
  public String getName();

  int attack(Warrior warrior);

  void setArmy(AlienArmy army);

  public void decideAttackStrength(Scanner s);
}
