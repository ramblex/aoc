package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day07Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 3749 for the example input" in {
    val result = Using(Source.fromResource("day07_example.txt")) { source =>
      new Day07Solution(source).part1()
    }
    result.get should be (3749)
  }

  "part2" should "return 11387 for the example input" in {
    val result = Using(Source.fromResource("day07_example.txt")) { source =>
      new Day07Solution(source).part2()
    }
    result.get should be (11387)
  }
}

