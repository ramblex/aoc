package aoc

import scala.io.Source
import scala.collection.mutable.{Map,Set,ArrayBuffer}

class Day09Solution(var source: Source) {
  def part1(): Long = {
    val line = source.mkString.replace("\n", "")
    checksum(line, 0, 0, line.length / 2, 0)
  }

  def part2(): Long = {
    val line = source.mkString.replace("\n", "")
    require(line.length % 2 == 1)
    var indexMapping = Map[Int,Int]()
    var gapSizes = Map[Int,Int]()
    var currentIdx = 0
    (0 to line.length - 1 by 2).foreach { i =>
      indexMapping(i) = currentIdx
      if (i < line.length - 1) {
        currentIdx += line(i).toString.toInt
        indexMapping(i + 1) = currentIdx
        gapSizes(i + 1) = line(i + 1).toString.toInt
        currentIdx += line(i + 1).toString.toInt
      }
    }
    noFragChecksum(line, line.length - 1, indexMapping, gapSizes, 0)
  }

  def noFragChecksum(input: String, idx: Int, indexMapping: Map[Int,Int], gapSizes: Map[Int,Int], result: Long): Long = {
    if (idx < 0) {
      return result
    }

    val fileNum = idx / 2
    val fileSize = input(idx).toString.toInt
    var newResult = result

    findGap(input, idx, fileSize, gapSizes) match {
      case Some(gapIdx) => {
        //printf("Mapped: %d -> %d\n", idx, gapIdx)
        newResult += fileChecksum(fileNum, indexMapping(gapIdx), fileSize)
        gapSizes(gapIdx) -= fileSize
        indexMapping(gapIdx) += fileSize
      }
      case None => {
        newResult += fileChecksum(fileNum, indexMapping(idx), fileSize)
      }
    }

    noFragChecksum(input, idx - 2, indexMapping, gapSizes, newResult)
  }

  def findGap(input: String, idx: Int, reqSize: Int, gapSizes: Map[Int, Int]): Option[Int] = {
    //printf(s"findGap($gapSizes). reqSize $reqSize\n")
    gapSizes.collect { case (key, value) if key < idx && value >= reqSize => key }.minOption
  }

  def checksum(input: String, idx: Long, fileno: Long, endfileno: Long, result: Long): Long = {
    if (input.isEmpty) {
      return result
    }

    val fileSize = input(0).toString.toInt
    var newResult = result + fileChecksum(fileno, idx, fileSize)
    if (input.length == 1) {
      return newResult
    }

    val space = input(1).toString.toInt
    filesForSpace(input, space) match {
      case (sizes, removedFiles, output) => {
        var x: Long = 0
        sizes.zipWithIndex.foreach { (s, newFileIdx) =>
          newResult += fileChecksum(endfileno - newFileIdx, idx + fileSize + x, s)
          x += s
        }

        checksum(output.drop(2), idx + fileSize + space, fileno + 1, endfileno - removedFiles, newResult)
      }
    }
  }

  def fileChecksum(fileno: Long, startIdx: Long, length: Int): Long = {
    // printf("fileno: %d, startidx: %d, length: %d\n", fileno, startIdx, length)
    // (1 to length).foreach { _ =>
    //   printf("%d", fileno)
    // }
    // println("")
    (0 to length - 1).foldLeft(0: Long) { (r, i) =>
      r + ((i.toLong + startIdx) * fileno)
    }
  }

  def filesForSpace(input: String, space: Int): (Array[Int], Int, String) = {
    if (input.length < 3) {
      return (Array(), 0, "")
    }

    var result = ArrayBuffer[Int]()
    var output = input
    var currentSize = 0
    var removedFiles = 0

    (1 to space).foreach { i =>
      currentSize += 1
      if (output.length % 2 == 0) {
        // Final item is a pair of (file size, space)
        val Array(f, s) = output.takeRight(2).split("").map(_.toString.toInt)
        if (f == 1) {
          output = output.dropRight(2)
          result += currentSize
          currentSize = 0
          removedFiles += 1
        } else {
          output = output.dropRight(2) + (f - 1)
        }
      } else {
        // Final item is a file size
        val f = output.last.toString.toInt
        if (f == 1) {
          output = output.dropRight(1)
          result += currentSize
          currentSize = 0
          removedFiles += 1
        } else {
          output = output.dropRight(1) + (f - 1)
        }
      }
    }

    if (currentSize > 0) {
      result += currentSize
    }

    //printf("%s -> %s [%s]\n", input, output, result.mkString(","))

    (result.toArray, removedFiles, output)
  }
}
