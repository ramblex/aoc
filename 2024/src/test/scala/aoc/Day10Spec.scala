package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day10Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 36 for the example input" in {
    val result = Using(Source.fromResource("day10_example.txt")) { source =>
      new Day10Solution(source).part1()
    }
    result.get should be (36)
  }

  "part2" should "return 81 for the example input" in {
    val result = Using(Source.fromResource("day10_example.txt")) { source =>
      new Day10Solution(source).part2()
    }
    result.get should be (81)
  }
}

