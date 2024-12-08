package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,ArrayBuffer}

class Day08Solution(var source: Source) {
  type Pos = (Int, Int)

  val lines = source.getLines().toArray

  def part1(): Int = {
    var antennas = Map[Char,Array[Pos]]()
    var antinodes = Set[Pos]()
    val validFrequencies = ('0' to '9').toSet ++ ('a' to 'z') ++ ('A' to 'Z')

    lines.zipWithIndex.foreach { (line, row) =>
      line.zipWithIndex.foreach { (c, col) =>
        if (validFrequencies.contains(c)) {
          val pos = (row, col)
          val existing = antennas.getOrElse(c, Array[Pos]())
          antinodes ++= findAntinodes(pos, existing)
          antennas.update(c, Array.concat(existing, Array(pos)))
        }
      }
    }

    antinodes.size
  }

  def part2(): Int = {
    var antennas = Map[Char,Array[Pos]]()
    var antinodes = Set[Pos]()
    val validFrequencies = ('0' to '9').toSet ++ ('a' to 'z') ++ ('A' to 'Z')

    lines.zipWithIndex.foreach { (line, row) =>
      line.zipWithIndex.foreach { (c, col) =>
        if (validFrequencies.contains(c)) {
          val pos = (row, col)
          val existing = antennas.getOrElse(c, Array[Pos]())
          existing.foreach { a => antinodes ++= part2Antinodes(pos, a) }
          antennas.update(c, Array.concat(existing, Array(pos)))
        }
      }
    }

    antinodes.size
  }

  private def part2Antinodes(pos1: Pos, pos2: Pos): Array[Pos] = {
    val dcol = pos2(1) - pos1(1)
    val drow = pos2(0) - pos1(0)

    var pos = pos1
    while (inBounds(pos)) { pos = (pos(0) - drow, pos(1) - dcol) }

    pos = (pos(0) + drow, pos(1) + dcol)
    var result = ArrayBuffer[Pos]()
    while (inBounds(pos)) {
      result += pos
      pos = (pos(0) + drow, pos(1) + dcol)
    }
    result.toArray
  }

  private def antinodes(pos1: Pos, pos2: Pos): Array[Pos] = {
    val dcol = pos2(1) - pos1(1)
    val drow = pos2(0) - pos1(0)

    Array(
      (pos1(0) - drow, pos1(1) - dcol),
      (pos2(0) + drow, pos2(1) + dcol)
    ).filter { p => inBounds(p) }
  }

  private def findAntinodes(pos: Pos, antennas: Array[Pos]): Array[Pos] = {
    var result = ArrayBuffer[Pos]()
    antennas.foreach { a => result.appendAll(antinodes(pos, a)) }
    result.toArray
  }

  private def inBounds(pos: Pos): Boolean = {
    pos(0) >= 0 && pos(0) < lines.length && pos(1) >= 0 && pos(1) < lines(0).length
  }
}
