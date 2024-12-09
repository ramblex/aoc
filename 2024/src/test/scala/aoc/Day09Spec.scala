package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day09Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 1928 for the example input" in {
    val result = Using(Source.fromResource("day09_example.txt")) { source =>
      new Day09Solution(source).part1()
    }
    result.get should be (1928)
  }

  "part2" should "return 2858 for the example input" in {
    val result = Using(Source.fromResource("day09_example.txt")) { source =>
      new Day09Solution(source).part2()
    }
    result.get should be (2858)
  }
}

