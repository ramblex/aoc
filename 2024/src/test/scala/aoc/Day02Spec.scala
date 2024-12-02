package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day02Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 2 for the example input" in {
    val result = Using(Source.fromResource("day02_example.txt")) { source =>
      new Day02Solution(source).part1()
    }
    result.get should be (2)
  }

  "part2" should "return 4 for the example input" in {
    val result = Using(Source.fromResource("day02_example.txt")) { source =>
      new Day02Solution(source).part2()
    }
    result.get should be (4)
  }
}
