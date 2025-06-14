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

  public Warrior(AlienArmy army) {
    // Set random amount of power for warrior
    Random r = new Random();
    this.power = r.nextInt(100, 200);

    this.army = army;

    this.visibility = null;
    this._telescope = new Telescope(this);
    this._satellite = new Satellite();
  }

  public int getPower() {
    return this.power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public int getHit(int armySize) {
    this.power -= armySize;
    return this.power;
  }

  public Observer getVisibility() {
    return this.visibility;
  }

  private void setVisibility(Visibility visibility) {
    this.visibility = visibility;
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

    return;
  }
}
