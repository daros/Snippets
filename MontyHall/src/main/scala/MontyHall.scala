package se.megalit.kata

import util.Random


/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: 2010-12-11
 * Time: 03:38
 */

class Hall {
  val carDoor = Random.nextInt(3) + 1
}

class Player {
  val firstChoise = Random.nextInt(3) + 1
}

class Monty(hall: Hall, player: Player) {
  val open = 1
}