package org.invasion;

import java.io.Console;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    Game game = new Game();
    game.showIntroduction();
    game.initializeWarrior(s);
    game.initializeAlienArmy();
    game.showState();

    try {
      game.mainLoop(s);
    } catch (Exception e) {
      s.close();
      throw e;
    }

  }

}
