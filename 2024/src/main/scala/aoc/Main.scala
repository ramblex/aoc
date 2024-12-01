package aoc

object aoc {
  def main(args: Array[String]): Unit = {
    args(0) match {
      case "1.1" => {
        scala.util.Using(scala.io.Source.fromResource("day01.txt")) { source => 
          println(new Day01Solution(source).part1())
        }
      }
      case "1.2" => {
        scala.util.Using(scala.io.Source.fromResource("day01.txt")) { source => 
          println(new Day01Solution(source).part2())
        }
      }
    }
  }
}
