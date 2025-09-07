package org.invasion.armies;

import java.util.Random;

import org.invasion.utils.Observer;
import org.invasion.visibility.Telescope;
import org.invasion.visibility.Satellite;
import org.invasion.visibility.Visibility;

public class Warrior implements Army {

  private int power;
  private Observer visibility;
  private Telescope _telescope;
  private Satellite _satellite;
  private AlienArmy army;
  public int lastAttackCount;

  public Warrior(AlienArmy army) {
    // Set random amount of power for warrior
    Random r = new Random();
    this.power = r.nextInt(100, 200);

    this.army = army;
    this.lastAttackCount = 0;

    this.visibility = null;
    this._telescope = new Telescope(this);
    this._satellite = new Satellite(this);
  }

  public void setArmy(AlienArmy army) {
    this.army = army;
  }

  public int getPower() {
    return this.power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public int getHit(int armySize) {
    this.power -= armySize;
    this.lastAttackCount = armySize;
    return this.power;
  }

  public Observer getVisibility() {
    return this.visibility;
  }

  private void setVisibility(Visibility visibility) {
    this.visibility = visibility;
  }

  public int attack() {
    // Check how many aliens were recorded from the visibility tool.
    // Telescope returns the entire amount of aliens attacking,
    // and Satellite returns only about 50% of the army's attack.
    int aliens = this.visibility.getAliens();
    this.army.getAttacked(aliens);

    return aliens;
  }

  public void decideVisibility(int attackOption) {
    if (attackOption == 1) {
      this.setVisibility(this._satellite);
      return;
    }

    if (attackOption == 2) {
      this.setVisibility(this._telescope);
      return;
    }
  }

  public void updateVisibility() {
    this._satellite.update();
    this._telescope.update();
  }
}
