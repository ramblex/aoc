package aoc

import scala.io.Source
import scala.collection.mutable.{ListBuffer, Map}

class Day04Solution(var source: Source) {
  val lines = source.getLines().toArray
  val directions = Array(
    Array(-1, -1),
    Array(0, -1),
    Array(1, -1),
    Array(1, 0),
    Array(1, 1),
    Array(0, 1),
    Array(-1, 1),
    Array(-1, 0)
  )

  def part1(): Integer = {
    lines.zipWithIndex.foldLeft(0) { case (result, (line, row)) =>
      result + line.zipWithIndex.foldLeft(0) { case (n, (ch, col)) =>
        ch match {
          case 'X' => n + countWords(row, col)
          case _ => n
        }
      }
    }
  }

  def part2(): Integer = {
    lines.zipWithIndex.foldLeft(0) { case (result, (line, row)) =>
      result + line.zipWithIndex.foldLeft(0) { case (n, (ch, col)) =>
        ch match {
          case 'A' if isCross(row, col) => n + 1
          case _ => n
        }
      }
    }
  }

  private def countWords(row: Integer, col: Integer): Integer = {
    directions.count { dir =>
      (1 to 3).map { i => letterAt(row + (i * dir(0)), col + (i * dir(1))) } == Vector('M', 'A', 'S')
    }
  }

  private def isCross(row: Integer, col: Integer): Boolean = {
    val corners = Vector(
      letterAt(row - 1, col - 1),
      letterAt(row - 1, col + 1),
      letterAt(row + 1, col + 1),
      letterAt(row + 1, col - 1)
    )

    corners match {
      case Vector('S', 'M', 'M', 'S') => true
      case Vector('M', 'M', 'S', 'S') => true
      case Vector('M', 'S', 'S', 'M') => true
      case Vector('S', 'S', 'M', 'M') => true
      case _ => false
    }
  }

  private def letterAt(row: Integer, col: Integer): Char = {
    if (row < 0 || row >= lines.length || col < 0 || col >= lines(0).length) {
      return 'N'
    }
    lines(row).charAt(col)
  }
}
