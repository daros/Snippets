package se.megalit.kata

import util.Random


/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: 2010-12-11
 * Time: 03:38
 */

object Hall extends Application {
  val choices = List.range(1, 6)

  println("Doors: "+choices)
  // Changer
  var wins = 0
  for (i <- 1 to 10000) {
    val hall = new Hall
    val monty = new Monty(hall) with LeftDoor
    val player = new Player with Changer

    val playerChoice = player.choice(monty.choice(player.firstChoice))
    if (playerChoice == hall.carDoor) wins += 1
  }
  println("changer wins: " + wins)

  // Keeper
  wins = 0
  for (i <- 1 to 10000) {
    val hall = new Hall
    val monty = new Monty(hall) with LeftDoor
    val player = new Player with Keeper

    val playerChoice = player.choice(monty.choice(player.firstChoice))
    if (playerChoice == hall.carDoor) wins += 1
  }
  println("keeper wins: " + wins)

  // Fickle
  wins = 0
  for (i <- 1 to 10000) {
    val hall = new Hall
    val monty = new Monty(hall) with LeftDoor
    val player = new Player with Fickle

    val playerChoice = player.choice(monty.choice(player.firstChoice))
    if (playerChoice == hall.carDoor) wins += 1
  }
  println("fickle wins: " + wins)

  // Gambler
  wins = 0
  for (i <- 1 to 10000) {
    val hall = new Hall
    val monty = new Monty(hall) with LeftDoor
    val player = new Player with Gambler

    val playerChoice = player.choice(monty.choice(player.firstChoice))
    if (playerChoice == hall.carDoor) wins += 1
  }
  println("gambler wins: " + wins)

  // Gambler
  wins = 0
  for (i <- 1 to 10000) {
    val hall = new Hall
    val monty = new Monty(hall) with RandomDoor
    val player = new Player with Gambler

    val playerChoice = player.choice(monty.choice(player.firstChoice))
    if (playerChoice == hall.carDoor) wins += 1
  }
  println("foolgambler wins: " + wins)
}

class Hall {
  val carDoor = Random.shuffle(Hall.choices).head
}

class Player {
  val firstChoice = Random.shuffle(Hall.choices).head
}

class Monty(val hall: Hall) {
}

trait LeftDoor {
  val hall: Hall
  def choice(playerFirstChoice: Int) = {
    Hall.choices.filter(_ != hall.carDoor).filter(_ != playerFirstChoice).head
  }
}

trait RandomDoor {
  val hall: Hall
  def choice(playerFirstChoice: Int) = {
    Random.shuffle(Hall.choices.filter(_ != hall.carDoor).filter(_ != playerFirstChoice)).head
  }
}

trait Changer {
  val firstChoice: Int

  def choice(montyChoice: Int): Int = {
    Hall.choices.filter(_ != montyChoice).filter(_ != firstChoice).head
  }
}

trait Keeper {
  val firstChoice: Int

  def choice(montyChoice: Int): Int = firstChoice
}

trait Fickle {
  val firstChoice: Int

  def choice(montyChoice: Int): Int = {
    Random.shuffle(Hall.choices.filter(_ != montyChoice)).head
  }
}

trait Gambler {
  val firstChoice: Int

  def choice(montyChoice: Int): Int = {
    if (Hall.choices.filter(_ != firstChoice).head == montyChoice) return firstChoice

    Hall.choices.filter(_ != montyChoice).filter(_ != firstChoice).head
  }
}