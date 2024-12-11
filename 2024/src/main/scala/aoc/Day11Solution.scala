package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,Queue,Stack}

class Day11Solution(var source: Source) {
  val numbers = source.getLines().mkString.split(" ").map(_.toLong)

  def part1(): Long = {
    transform(numbers, 25)
  }

  def part2(): Long = {
    transform(numbers, 75)
  }

  private def transform(numbers: Array[Long], targetBlinks: Int): Long = {
    // (n, blinks) => solution
    var solutions = Map[(Long, Int), Long]()

    def visit(n: Long, blinks: Int): Long = {
      if (blinks == 0) {
        1
      } else if (solutions.contains((n, blinks))) {
        solutions((n, blinks))
      } else {
        val result = nextStones(n).foldLeft(0L) { (acc, s) =>  acc + visit(s, blinks - 1) }
        solutions((n, blinks)) = result
        result
      }
    }

    numbers.foldLeft(0L) { (acc, n) => acc + visit(n, targetBlinks) }
  }

  private def nextStones(n: Long): Array[Long] = {
    n match {
      case 0 => Array(1.toLong)
      case n if n.toString.length % 2 == 0 => {
        val half = n.toString.length / 2
        Array(n.toString.take(half).toLong, n.toString.drop(half).toLong)
      }
      case _ => Array(n * 2024)
    }
  }
}
