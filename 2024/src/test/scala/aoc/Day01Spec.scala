package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day01Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "match the example input solution" in {
    val result = Using(Source.fromResource("day01_example.txt")) { source =>
      new Day01Solution(source).part1()
    }
    result.get should be (11)
  }

  "part2" should "return 31 for the example input" in {
    val result = Using(Source.fromResource("day01_example.txt")) { source =>
      new Day01Solution(source).part2()
    }
    result.get should be (31)
  }
}
