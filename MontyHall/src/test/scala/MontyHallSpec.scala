package se.megalit.kata

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: 2010-12-11
 * Time: 03:11
 */

class MontyHallSpec extends Spec with ShouldMatchers {
  describe("Hall's") {
    val hall = new Hall

    describe("will have one car behind a door") {
      it("remember which door has the car") {
        hall.carDoor should be(hall.carDoor)
      }
      it("should return the door in range") {
        hall.carDoor >= Hall.choices.min should be(true)
        hall.carDoor <= Hall.choices.max should be(true)
      }
    }
    describe("carDoor should be generated randomly") {
      var differentDoors = false
      for (i <- 1 to 100) {
        val testHall = new Hall
        if (hall.carDoor != testHall.carDoor) differentDoors = true
      }
      it("this should happend at least once probably") {
        differentDoors should be(true)
      }
    }
    describe("choices") {
      it("should be at least List(1,2,3)") {
        Hall.choices.size > 3  should be(true)
      }
    }
  }

  describe("Player") {
    describe("first choise") {
      val player = new Player

      it("should be remembered") {
        player.firstChoice should be(player.firstChoice)
      }
      it("should return the door in range") {
        player.firstChoice >= Hall.choices.min should be(true)
        player.firstChoice <= Hall.choices.max should be(true)
      }

      describe("should be random") {
        var differentDoors = false
        for (i <- 1 to 100) {
          val testPlayer = new Player
          if (player.firstChoice != testPlayer.firstChoice) differentDoors = true
        }
        it("this should happend at least once probably") {
          differentDoors should be(true)
        }
      }
    }
  }

  describe("Monty") {
    describe("opens door") {
      val hall = new Hall
      val player = new Player
      val monty = new Monty(hall) with LeftDoor
      val montyChoice = monty.choice(player.firstChoice)

      it("other than carDoor") {
        montyChoice should not be (hall.carDoor)
      }
      it("other than player firstChoice") {
        montyChoice should not be (player.firstChoice)
      }
    }
  }

  describe("Player") {
    describe("final choice") {
      val player = new Player with Keeper
      val hall = new Hall
      val monty = new Monty(hall) with LeftDoor
      val montyChoice = monty.choice(player.firstChoice);

      it("should not be monty's choice") {
        player.choice(montyChoice) should not be (montyChoice)
      }
    }

    describe("final choice with changer strategy") {
      val player = new Player with Changer
      val hall = new Hall
      val monty = new Monty(hall) with LeftDoor
      val montyChoice = monty.choice(player.firstChoice);

      it("should change") {
        player.choice(montyChoice) should not be (player.firstChoice)
      }
    }

    describe("final choice with keeper strategy") {
      val player = new Player with Keeper
      val hall = new Hall
      val monty = new Monty(hall) with LeftDoor
      val montyChoice = monty.choice(player.firstChoice);

      it("should stay with first choice") {
        player.choice(montyChoice) should be(player.firstChoice)
      }
    }

    describe("final choice with fickle strategy") {
      var player = new Player with Fickle
      val hall = new Hall
      val monty = new Monty(hall) with LeftDoor

      var montyChoice = monty.choice(player.firstChoice);
      var playerChoice = player.choice(montyChoice);

      var differentChoices = false
      val choiceOne = playerChoice == player.firstChoice
      for (i <- 1 to 100) {
        player = new Player with Fickle
        montyChoice = monty.choice(player.firstChoice);
        playerChoice = player.choice(montyChoice);

        if (choiceOne) {
          if (playerChoice != player.firstChoice) differentChoices = true
        } else {
          if (playerChoice == player.firstChoice) differentChoices = true
        }
      }

      it("should changing") {
        differentChoices should be(true)
      }
    }
  }
}