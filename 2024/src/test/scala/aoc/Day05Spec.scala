package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day05Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 143 for the example input" in {
    val result = Using(Source.fromResource("day05_example.txt")) { source =>
      new Day05Solution(source).part1()
    }
    result.get should be (143)
  }

  "part2" should "return 123 for the example input" in {
    val result = Using(Source.fromResource("day05_example.txt")) { source =>
      new Day05Solution(source).part2()
    }
    result.get should be (123)
  }
}
