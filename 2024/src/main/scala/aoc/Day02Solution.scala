package aoc

import scala.io.Source
import scala.collection.mutable.{ListBuffer, Map}

class Day02Solution(var source: Source) {
  def part1(): Integer = {
    source.getLines().count { l => isSafe(lineToLevels(l)) }
  }

  def part2(): Integer = {
    source.getLines().count { l => isSafeWithRemoval(lineToLevels(l)) }
  }

  private def lineToLevels(line: String): Array[Int] = line.split(" ").map(_.toInt)

  private def isSafeWithRemoval(levels: Array[Int]): Boolean = {
    isSafe(levels) || levels.indices.exists { n => isSafe(levels.patch(n, Nil, 1)) }
  }

  private def isSafe(levels: Array[Int]): Boolean = {
    levels.sliding(2).foldLeft(("", true)) { (acc, pair) =>
      var (dir, result) = acc
      if (dir == "") {
        dir = direction(pair)
      }
      (dir, result && isSafePair(dir, pair))
    }(1)
  }

  private def direction(pair: Array[Int]): String = {
    if (pair(0) < pair(1)) {
      "asc"
    } else if (pair(0) > pair(1)) {
      "desc"
    } else {
      ""
    }
  }

  private def isSafePair(dir: String, pair: Array[Int]): Boolean = {
    val diff = Math.abs(pair(0) - pair(1))
    (dir == "" || direction(pair) == dir) && (diff >= 1 && diff <= 3)
  }
}
