package org.invasion.visibility;

import org.invasion.armies.Warrior;

public class Satellite implements Visibility {

  private Warrior warrior;

  public Satellite(Warrior warrior) {
    this.warrior = warrior;
  }

  @Override
  public void update() {

  }

}
