
package aoc

import org.scalatest._
import flatspec._
import matchers._
import scala.util.Using
import scala.io.Source

class Day12Spec extends AnyFlatSpec with should.Matchers {
  "part1" should "return 1930 for the example input" in {
    val result = Using(Source.fromResource("day12_example.txt")) { source =>
      new Day12Solution(source).part1()
    }
    result.get should be (1930)
  }

  "part2" should "return 1206 for the example input" in {
    val result = Using(Source.fromResource("day12_example.txt")) { source =>
      new Day12Solution(source).part2()
    }
    result.get should be (1206)
  }
}

