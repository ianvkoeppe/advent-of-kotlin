package io

import java.nio.file.Files
import java.nio.file.Paths

object Reader {
  fun readExample(year: Int, day: Int): List<String> = read("example.txt", year, day)
  fun readProblem(year: Int, day: Int): List<String> = read("problem.txt", year, day)
  fun read(filename: String, year: Int, day: Int): List<String> = Files.readAllLines(Paths.get("src/test/resources/$year/$day/$filename")).map { it.trim() }
}