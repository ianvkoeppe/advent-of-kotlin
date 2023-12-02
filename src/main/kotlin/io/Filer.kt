package io

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object Filer {
  fun readExample(year: Int, day: Int): List<String> = read(year, day, "example.txt")

  fun readProblem(year: Int, day: Int): List<String> = read(year, day, "problem.txt")

  fun read(year: Int, day: Int, filename: String): List<String> =
    Files.readAllLines(Paths.get("src/test/resources/$year/$day/$filename"))

  fun writeStringCreatingDirectories(path: Path, content: String) {
    Files.createDirectories(path.parent)
    Files.writeString(path, content)
  }
}
