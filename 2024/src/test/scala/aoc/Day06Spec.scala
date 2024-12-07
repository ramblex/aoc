package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day06Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 41 for the example input" in {
    val result = Using(Source.fromResource("day06_example.txt")) { source =>
      new Day06Solution(source).part1()
    }
    result.get should be (41)
  }

  "part2" should "return 6 for the example input" in {
    val result = Using(Source.fromResource("day06_example.txt")) { source =>
      new Day06Solution(source).part2()
    }
    result.get should be (6)
  }
}
