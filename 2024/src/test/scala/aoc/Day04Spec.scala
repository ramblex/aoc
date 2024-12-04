package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day04Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 18 for the example input" in {
    val result = Using(Source.fromResource("day04_example.txt")) { source =>
      new Day04Solution(source).part1()
    }
    result.get should be (18)
  }

  "part2" should "return 9 for the example input" in {
    val result = Using(Source.fromResource("day04_example.txt")) { source =>
      new Day04Solution(source).part2()
    }
    result.get should be (9)
  }
}
