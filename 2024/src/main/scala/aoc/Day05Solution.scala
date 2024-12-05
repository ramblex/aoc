package aoc

import scala.io.Source
import scala.collection.mutable.{ArrayBuffer, Map}

class Day05Solution(var source: Source) {
  val Array(ruleLines, updateLines) = source.mkString.split("\n\n")

  var rules: Map[Int,Array[Int]] = Map()
  val rulePattern = """^(\d+)\|(\d+)$""".r
  ruleLines.split("\n").foreach {
    case rulePattern(l, r) => {
      rules.update(l.toInt, Array.concat(rules.getOrElse(l.toInt, Array()), Array(r.toInt)))
    }
  }

  def part1(): Integer = {
    var result = 0
    updateLines.split("\n").foreach { line =>
      val numbers = line.split(",").map(_.toInt)
      if (isValid(numbers)) {
        result += numbers(numbers.length / 2)
      }
    }
    result
  }

  def part2(): Integer = {
    var result = 0
    updateLines.split("\n").foreach { line =>
      val numbers = line.split(",").map(_.toInt)
      if (!isValid(numbers)) {
        val fixedNumbers = fixNumbers(numbers)
        result += fixedNumbers(fixedNumbers.length / 2)
      }
    }
    result
  }

  private def isValid(numbers: Array[Int]): Boolean = {
    (0 to numbers.length - 1).forall { x =>
      (x + 1 to numbers.length - 1).forall { y =>
        isValidPair(numbers(x), numbers(y))
      }
    }
  }

  // Checks that y can follow x
  private def isValidPair(x: Int, y: Int): Boolean = {
    !rules.getOrElse(y, Array[Int]()).contains(x)
  }

  private def fixNumbers(numbers: Array[Int]): Array[Int] = {
    val result = ArrayBuffer[Int]()
    numbers.foreach { n =>
      val insertIdx = result.reverseIterator.zipWithIndex.collectFirst {
        case (elem, idx) if !isValidPair(elem, n) => result.length - idx
      }
      result.insert(insertIdx.getOrElse(0), n)
    }
    result.toArray
  }
}
