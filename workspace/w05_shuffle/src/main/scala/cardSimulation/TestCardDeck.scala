package cardSimulation

import scala.collection.mutable

object TestCardDeck {
  def testCreate(): Unit = {
    println("\n*** testCreate:")
    val deck = CardDeck.full() ; println(s"FULL DECK:\n$deck")
    deck.shuffle()             ; println(s"SHUFFLED: \n$deck")
    deck.reset()               ; println(s"RESET:    \n$deck")
  }

  def testShuffle(n: Long = 10000000L): Unit = {
    println("\n*** testShuffle:")
    val testCards = Vector(Card(11, 1), Card(12, 1), Card(13, 1))
    val perms: Vector[Vector[Card]] = testCards.permutations.toVector
    val freq: Array[Long] = Array.fill(perms.length)(0L)
    val deck = CardDeck(testCards)
    for (i <- 1L to n) {
      if (i % 1e6.toLong == 0L) print(".")
      deck.shuffle()
      val permIndex = perms.indexOf(deck.toVector)
      freq(permIndex) += 1L
    }
    val listingOfPercentages = perms.indices
      .map(i => s"${perms(i).mkString}: ${freq(i).toDouble/n}%")
      .mkString("\n")
    println(s"\n$listingOfPercentages")
  }

  def main(args: Array[String]): Unit = {
    testCreate()
    testShuffle()
  }
}
