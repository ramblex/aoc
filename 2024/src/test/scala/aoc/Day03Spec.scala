package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day03Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 161 for the example input" in {
    val result = Using(Source.fromResource("day03_example.txt")) { source =>
      new Day03Solution(source).part1()
    }
    result.get should be (161)
  }

  "part2" should "return 48 for the example input" in {
    val result = Using(Source.fromResource("day03_example2.txt")) { source =>
      new Day03Solution(source).part2()
    }
    result.get should be (48)
  }
}
