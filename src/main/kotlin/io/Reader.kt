package io

import java.nio.file.Files
import java.nio.file.Paths

object Reader {
  fun read(path: String): List<String> = Files.readAllLines(Paths.get(path))
}