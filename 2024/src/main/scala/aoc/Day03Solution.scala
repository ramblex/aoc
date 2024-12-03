package aoc

import scala.io.Source
import scala.collection.mutable.{ListBuffer, Map}

class Day03Solution(var source: Source) {
  def part1(): Integer = {
    val pattern = "mul\\(([0-9]+),([0-9]+)\\)".r
    pattern.findAllMatchIn(source.mkString).foldLeft(0) { (acc, m) =>
      acc + (m.group(1).toInt * m.group(2).toInt)
    }
  }

  def part2(): Integer = {
    val pattern = "(do|mul|don't)\\((([0-9]+),([0-9]+))?\\)".r
    pattern.findAllMatchIn(source.mkString).foldLeft((0, true)) { case ((res, en), m) =>
      (m.group(1), m.group(2), en) match {
        case ("mul", args: String, true) if args != null => {
          (res + (m.group(3).toInt * m.group(4).toInt), en)
        }
        case ("do", null, _) => (res, true)
        case ("don't", null, _) => (res, false)
        case _ => (res, en)
      }
    }._1
  }
}
