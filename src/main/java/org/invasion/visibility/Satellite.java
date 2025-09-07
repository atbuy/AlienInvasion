package org.invasion.visibility;

import java.util.Random;

import org.invasion.armies.Warrior;

public class Satellite implements Visibility {

  private Warrior warrior;
  private int _recordedAliens;

  public Satellite(Warrior warrior) {
    this.warrior = warrior;
    this._recordedAliens = 0;
  }

  @Override
  public void update() {
    int attacked = this.warrior.lastAttackCount;
    double half = attacked * 0.5;
    int randomness = new Random().nextInt(1, (int) half);
    this._recordedAliens = (int) (half - randomness);
  }

  public int getAliens() {
    // Return how many aliens were recorded in the last attack
    return this._recordedAliens;
  }

}
