package aoc

object aoc {
  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      return
    }

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
      case "2.1" => {
        scala.util.Using(scala.io.Source.fromResource("day02.txt")) { source => 
          println(new Day02Solution(source).part1())
        }
      }
      case "2.2" => {
        scala.util.Using(scala.io.Source.fromResource("day02.txt")) { source => 
          println(new Day02Solution(source).part2())
        }
      }
      case _ => { println("Not a valid day/part") }
    }
  }
}
