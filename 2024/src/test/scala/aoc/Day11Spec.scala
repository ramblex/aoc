package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day11Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 55312 for the example input" in {
    val result = Using(Source.fromResource("day11_example.txt")) { source =>
      new Day11Solution(source).part1()
    }
    result.get should be (55312)
  }
}

