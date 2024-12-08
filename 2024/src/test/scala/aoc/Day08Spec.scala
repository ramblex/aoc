package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day08Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 14 for the example input" in {
    val result = Using(Source.fromResource("day08_example.txt")) { source =>
      new Day08Solution(source).part1()
    }
    result.get should be (14)
  }

  "part2" should "return 34 for the example input" in {
    val result = Using(Source.fromResource("day08_example.txt")) { source =>
      new Day08Solution(source).part2()
    }
    result.get should be (34)
  }
}

