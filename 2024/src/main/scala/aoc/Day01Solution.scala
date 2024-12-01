package aoc

import scala.io.Source
import scala.collection.mutable.{ListBuffer, Map}

class Day01Solution(var source: Source) {
  private var lhs: ListBuffer[Integer] = ListBuffer()
  private var rhs: ListBuffer[Integer] = ListBuffer()

  for (line <- source.getLines()) {
    val parts = line.split("   ")
    lhs += parts(0).toInt
    rhs += parts(1).toInt
  }

  def part1(): Integer = {
    lhs.sorted.zip(rhs.sorted).map { (l, r) => Math.abs(l - r) }.sum
  }

  def part2(): Integer = {
    val counts: Map[Int, Int] = Map()
    rhs.foreach { case (v) => counts.update(v, counts.getOrElse(v, 0) + 1) }
    lhs.map { v => v * counts.getOrElse(v, 0) }.sum
  }
}
