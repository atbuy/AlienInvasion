package org.invasion.armies;

public class AlienArmy implements Army {
  private int armySize;

  public int getArmySize() {
    return this.armySize;
  }

  public void setArmySize(int armySize) {
    this.armySize = armySize;
  }

  public void getAttacked(int count) {
    int armySize = this.armySize;
    int newSize = armySize - count;
    this.setArmySize(newSize);
  }
}
