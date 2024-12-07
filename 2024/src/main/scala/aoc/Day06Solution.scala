package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,ArrayBuffer}
import scala.annotation.tailrec

class Day06Solution(var source: Source) {
  type Pos = (Int, Int)

  val directions = Array(
    (-1, 0),
    (0, 1),
    (1, 0),
    (0, -1)
  )

  val lines = source.mkString.split("\n")

  def part1(): Integer = {
    var visited = Set[Pos]()
    walk(initialPosition(lines).get, 0, visited)
    visited.size
  }

  def part2(): Integer = {
    val start = initialPosition(lines).get
    walkWithBlock(start, None, 0, ArrayBuffer[Pos]())
  }

  private def walkWithBlock(pos: Pos, block: Option[Pos], dir: Int, visited: ArrayBuffer[Pos]): Int = {
    if (!visited.isEmpty && !block.isEmpty && isLoop(pos, visited)) {
      return 1
    }

    visited += pos
    val nextPos = move(pos, dir)
    val nextChar = if (block == Some(nextPos)) Some('#') else charAt(nextPos)
    nextChar match {
      case Some('#') => {
        val nextDir = (dir + 1) % directions.length
        walkWithBlock(move(pos, nextDir), block, nextDir, visited)
      }
      case Some('.') => {
        if (block.isEmpty) {
          walkWithBlock(pos, Some(nextPos), dir, visited.clone()) + walkWithBlock(nextPos, block, dir, visited)
        } else {
          walkWithBlock(nextPos, block, dir, visited)
        }
      }
      case Some('^') => walkWithBlock(nextPos, block, dir, visited)
      case _ => 0
    }
  }

  private def isLoop(pos: Pos, visited: ArrayBuffer[Pos]): Boolean = {
    findRunLength(pos, visited) match {
      case None => false
      case Some(runLength) => {
        runLength > 0 &&
        visited.length > runLength * 2 &&
        (1 to runLength).forall { idx =>
          visited(visited.length - idx) == visited(visited.length - idx - runLength - 1)
        }
      }
    }
  }

  private def findRunLength(pos: Pos, arr: ArrayBuffer[Pos]): Option[Int] = {
    arr.reverseIterator.zipWithIndex.collectFirst { case (n, idx) if pos == n => idx }
  }

  @tailrec
  private def walk(pos: Pos, dir: Int, visited: Set[Pos]): Unit = {
    visited += pos
    val nextPos = move(pos, dir)
    charAt(nextPos) match {
      case Some('#') => {
        val nextDir = (dir + 1) % directions.length
        walk(move(pos, nextDir), nextDir, visited)
      }
      case Some('^') | Some('.') => walk(nextPos, dir, visited)
      case _ => {}
    }
  }

  private def charAt(pos: Pos): Option[Char] = {
    if (pos(0) < 0 || pos(0) >= lines.length || pos(1) < 0 || pos(1) >= lines(0).length) {
      return None
    }

    Some(lines(pos(0)).charAt(pos(1)))
  }

  private def move(pos: (Int, Int), dir: Int): (Int, Int) = {
    val direction = directions(dir)
    (pos(0) + direction(0), pos(1) + direction(1))
  }

  private def initialPosition(lines: Array[String]): Option[(Int, Int)] = {
    lines.zipWithIndex.collectFirst {
      case (l, row) if l.indexOf('^') >= 0 => (row, l.indexOf('^'))
    }
  }
}
