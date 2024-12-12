package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,Queue,Stack}

class Day12Solution(var source: Source) {
  type Pos = (Int, Int)
  extension (pos: Pos)
    def +(other: Pos): Pos = (pos._1 + other._1, pos._2 + other._2)
    def *(other: Pos): Pos = (pos._1 * other._1, pos._2 * other._2)

  val directions = Array[Pos](
    (-1, 0),
    (0, 1),
    (1, 0),
    (0, -1)
  )

  val lines = source.getLines().toArray

  def part1(): Int = {
    var visited = Set[Pos]()
    var result = 0

    lines.zipWithIndex.foreach { (line, row) =>
      line.zipWithIndex.foreach { (c, col) =>
        val start = (row, col)
        if (!visited.contains(start)) {
          var perimeter = 0
          var area = 0
          var q = Queue(start)

          while (q.nonEmpty) {
            val pos = q.dequeue()
            if (!visited.contains(pos)) {
              visited += pos
              area += 1
              perimeter += edges(pos)
              val adj = adjacentPositions(pos)
              q.enqueueAll(adj)
            }
          }

          result += (perimeter * area)
        }
      }
    }

    result
  }

  def part2(): Int = {
    var visited = Set[Pos]()
    var result = 0

    lines.zipWithIndex.foreach { (line, row) =>
      line.zipWithIndex.foreach { (c, col) =>
        val start = (row, col)
        if (!visited.contains(start)) {
          var upFences = Map[Int,Set[Int]]()
          var downFences = Map[Int,Set[Int]]()
          var leftFences = Map[Int,Set[Int]]()
          var rightFences = Map[Int,Set[Int]]()
          var area = 0
          var q = Queue(start)

          while (q.nonEmpty) {
            val pos = q.dequeue()
            if (!visited.contains(pos)) {
              visited += pos
              area += 1
              edgePositions(pos).foreach { (p, dir) =>
                dir match {
                  case (-1, 0) => upFences(p(0)) = upFences.getOrElse(p(0), Set()) + p(1)
                  case (1, 0) => downFences(p(0)) = downFences.getOrElse(p(0), Set()) + p(1)
                  case (0, -1) => leftFences(p(1)) = leftFences.getOrElse(p(1), Set()) + p(0)
                  case (0, 1) => rightFences(p(1)) = rightFences.getOrElse(p(1), Set()) + p(0)
                }
              }
              val adj = adjacentPositions(pos)
              q.enqueueAll(adj)
            }
          }

          val up = upFences.values.map { fenceCount(_) }.sum
          val down = downFences.values.map { fenceCount(_) }.sum
          val left = leftFences.values.map { fenceCount(_) }.sum
          val right = rightFences.values.map { fenceCount(_) }.sum

          result += area * (up + down + left + right)
        }
      }
    }

    result
  }

  def fenceCount(positions: Set[Int]): Int = {
    positions.toList.sorted.sliding(2).foldLeft(1) { (acc, pair) =>
      pair.size match {
        case 1 => acc
        case 2 if pair.last - pair.head > 1 => acc + 1
        case _ => acc
      }
    }
  }

  def edgePositions(pos: Pos): Array[(Pos, Pos)] = {
    directions.collect { case d if !inBounds(pos + d) || !sameRegion(pos, pos + d) => (pos + d, d) }
  }

  def edges(pos: Pos): Int = {
    directions.count(d => !inBounds(pos + d) || !sameRegion(pos, pos + d))
  }

  def adjacentPositions(pos: Pos): Array[Pos] = {
    directions.collect { case d if sameRegion(pos, pos + d) => pos + d }
  }

  def sameRegion(pos1: Pos, pos2: Pos): Boolean = {
    charAt(pos1) == charAt(pos2)
  }

  def inBounds(pos: Pos): Boolean = {
    pos(0) >= 0 && pos(1) >= 0 && pos(0) < lines.length && pos(1) < lines(0).length
  }

  def charAt(pos: Pos): Option[Char] = {
    if (inBounds(pos)) {
      Some(lines(pos(0)).charAt(pos(1)))
    } else {
      None
    }
  }
}
