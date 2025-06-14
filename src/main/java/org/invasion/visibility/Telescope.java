package org.invasion.visibility;

import org.invasion.armies.Warrior;

public class Telescope implements Visibility {

  private Warrior warrior;

  public Telescope(Warrior warrior) {
    this.warrior = warrior;
  }

  @Override
  public void update() {

  }
}
