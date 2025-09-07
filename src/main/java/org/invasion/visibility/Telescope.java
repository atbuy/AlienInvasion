package org.invasion.visibility;

import org.invasion.armies.Warrior;

public class Telescope implements Visibility {

  private Warrior warrior;
  private int _recordedSoldiers;

  public Telescope(Warrior warrior) {
    this.warrior = warrior;
    this._recordedSoldiers = 0;
  }

  @Override
  public void update() {
    this._recordedSoldiers = this.warrior.lastAttackCount;
  }

  @Override
  public int getAliens() {
    // Telescope returns the entire amount of the last attack count
    return this._recordedSoldiers;
  }
}
