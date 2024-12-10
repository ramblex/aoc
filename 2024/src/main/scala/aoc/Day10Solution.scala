package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,Queue,Stack}

class Day10Solution(var source: Source) {
  type Pos = (Int, Int)
  extension (pos: Pos)
    def +(other: Pos): Pos = (pos._1 + other._1, pos._2 + other._2)

  val lines = source.getLines().toArray
  val directions = Array[Pos](
    (-1, 0),
    (0, 1),
    (1, 0),
    (0, -1)
  )

  def part1(): Int = {
    zeroPositions().foldLeft(0) { (acc, pos) => acc + countPaths(pos, countAll = false) }
  }

  def part2(): Int = {
    zeroPositions().foldLeft(0) { (acc, pos) => acc + countPaths(pos, countAll = true) }
  }

  def zeroPositions(): Array[Pos] = {
    lines.zipWithIndex.flatMap {
      (line, row) => line.zipWithIndex.collect { case (c, col) if c == '0' => (row, col) }
    }
  }

  def countPaths(start: Pos, countAll: Boolean): Int = {
    var q = Queue(start)
    var visited = Set[Pos]()
    var result = 0
    
    while (q.nonEmpty) {
      val pos = q.dequeue()
      if (countAll || !visited.contains(pos)) {
        visited.add(pos)
        digitAt(pos) match {
          case Some(x) if x == 9 => result += 1
          case _ => q.enqueueAll(adjacentPositions(pos))
        }
      }
    }

    result
  }

  def adjacentPositions(pos: Pos): Array[Pos] = {
    directions.collect { case d if canTraverse(pos, pos + d) => pos + d }
  }

  def canTraverse(from: Pos, to: Pos): Boolean = {
    (digitAt(from), digitAt(to)) match {
      case (Some(fromDigit), Some(toDigit)) => fromDigit + 1 == toDigit
      case _ => false
    }
  }

  def inBounds(pos: Pos): Boolean = {
    pos(0) >= 0 && pos(1) >= 0 && pos(0) < lines.length && pos(1) < lines(0).length
  }

  def digitAt(pos: Pos): Option[Int] = {
    if (inBounds(pos)) {
      Some(lines(pos(0)).charAt(pos(1)).toString.toInt)
    } else {
      None
    }
  }
}
