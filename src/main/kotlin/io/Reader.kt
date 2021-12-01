package io

import java.nio.file.Files
import java.nio.file.Paths

object Reader {
  fun read(fileName: String): List<String> = Files.readAllLines(Paths.get("src/main/resources/$fileName"))
}