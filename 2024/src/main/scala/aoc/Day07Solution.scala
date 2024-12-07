package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,ArrayBuffer}

class Day07Solution(var source: Source) {
  def part1(): Long = {
    source.getLines().map { line =>
      val Array(target, opStr) = line.split(": ") 
      var operands = opStr.split(" ").map(_.toLong)
      if (hasSolution(target.toLong, operands.head, operands.drop(1))) {
        target.toLong
      } else {
        0
      }
    }.sum
  }

  def hasSolution(target: Long, head: Long, tail: Array[Long]): Boolean = {
    if (tail.isEmpty || head > target) {
      return false
    }

    if (tail.length == 1) {
      return (head * tail(0) == target) || (head + tail(0) == target)
    }

    hasSolution(target, head * tail(0), tail.drop(1)) ||
      hasSolution(target, head + tail(0), tail.drop(1))
  }

  def part2(): Long = {
    source.getLines().map { line =>
      val Array(target, opStr) = line.split(": ") 
      var operands = opStr.split(" ").map(_.toLong)
      if (hasPart2Solution(target.toLong, operands.head, operands.drop(1))) {
        target.toLong
      } else {
        0
      }
    }.sum
  }

  def hasPart2Solution(target: Long, head: Long, tail: Array[Long]): Boolean = {
    if (tail.isEmpty || head > target) {
      false
    } else if (tail.length == 1) {
      (head * tail(0) == target) || (head + tail(0) == target) || concat(head, tail(0)) == target
    } else {
      hasPart2Solution(target, head * tail(0), tail.drop(1)) ||
        hasPart2Solution(target, head + tail(0), tail.drop(1)) ||
        hasPart2Solution(target, concat(head, tail(0)), tail.drop(1))
    }
  }

  def concat(lhs: Long, rhs: Long): Long = {
    (lhs.toString + rhs.toString).toLong
  }
}
