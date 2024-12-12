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
      case "3.1" => {
        scala.util.Using(scala.io.Source.fromResource("day03.txt")) { source => 
          println(new Day03Solution(source).part1())
        }
      }
      case "3.2" => {
        scala.util.Using(scala.io.Source.fromResource("day03.txt")) { source => 
          println(new Day03Solution(source).part2())
        }
      }
      case "4.1" => {
        scala.util.Using(scala.io.Source.fromResource("day04.txt")) { source => 
          println(new Day04Solution(source).part1())
        }
      }
      case "4.2" => {
        scala.util.Using(scala.io.Source.fromResource("day04.txt")) { source => 
          println(new Day04Solution(source).part2())
        }
      }
      case "5.1" => {
        scala.util.Using(scala.io.Source.fromResource("day05.txt")) { source => 
          println(new Day05Solution(source).part1())
        }
      }
      case "5.2" => {
        scala.util.Using(scala.io.Source.fromResource("day05.txt")) { source => 
          println(new Day05Solution(source).part2())
        }
      }
      case "6.1" => {
        scala.util.Using(scala.io.Source.fromResource("day06.txt")) { source => 
          println(new Day06Solution(source).part1())
        }
      }
      case "6.2" => {
        scala.util.Using(scala.io.Source.fromResource("day06.txt")) { source => 
          println(new Day06Solution(source).part2())
        }
      }
      case "7.1" => {
        scala.util.Using(scala.io.Source.fromResource("day07.txt")) { source => 
          println(new Day07Solution(source).part1())
        }
      }
      case "7.2" => {
        scala.util.Using(scala.io.Source.fromResource("day07.txt")) { source => 
          println(new Day07Solution(source).part2())
        }
      }
      case "8.1" => {
        scala.util.Using(scala.io.Source.fromResource("day08.txt")) { source => 
          println(new Day08Solution(source).part1())
        }
      }
      case "8.2" => {
        scala.util.Using(scala.io.Source.fromResource("day08.txt")) { source => 
          println(new Day08Solution(source).part2())
        }
      }
      case "9.1" => {
        scala.util.Using(scala.io.Source.fromResource("day09.txt")) { source => 
          println(new Day09Solution(source).part1())
        }
      }
      case "9.2" => {
        scala.util.Using(scala.io.Source.fromResource("day09.txt")) { source => 
          println(new Day09Solution(source).part2())
        }
      }
      case "10.1" => {
        scala.util.Using(scala.io.Source.fromResource("day10.txt")) { source => 
          println(new Day10Solution(source).part1())
        }
      }
      case "10.2" => {
        scala.util.Using(scala.io.Source.fromResource("day10.txt")) { source => 
          println(new Day10Solution(source).part2())
        }
      }
      case "11.1" => {
        scala.util.Using(scala.io.Source.fromResource("day11.txt")) { source => 
          println(new Day11Solution(source).part1())
        }
      }
      case "11.2" => {
        scala.util.Using(scala.io.Source.fromResource("day11.txt")) { source => 
          println(new Day11Solution(source).part2())
        }
      }
      case "12.1" => {
        scala.util.Using(scala.io.Source.fromResource("day12.txt")) { source => 
          println(new Day12Solution(source).part1())
        }
      }
      case "12.2" => {
        scala.util.Using(scala.io.Source.fromResource("day12.txt")) { source => 
          println(new Day12Solution(source).part2())
        }
      }
      case _ => { println("Not a valid day/part") }
    }
  }
}
