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
        hall.carDoor > 0 should be(true)
        hall.carDoor < 4 should be(true)
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
  }

  describe("Player") {
    val player = new Player
    describe("first choise") {

      it("should be remembered") {
        player.firstChoise should be(player.firstChoise)
      }
      it("should return the door in range") {
        player.firstChoise > 0 should be(true)
        player.firstChoise < 4 should be(true)
      }

      describe("should be random") {
        var differentDoors = false
        for (i <- 1 to 100) {
          val testPlayer = new Player
          if (player.firstChoise != testPlayer.firstChoise) differentDoors = true
        }
        it("this should happend at least once probably") {
          differentDoors should be(true)
        }
      }
    }
  }

  describe("Monty") {
    val hall = new Hall
    val player = new Player
    val monty = new Monty(hall, player)

    describe("opens door") {
      it("other than carDoor and player firstChoise") {
        monty.open should not be(hall.carDoor)
        monty.open should not be(player.firstChoise)
      }
    }
  }
}