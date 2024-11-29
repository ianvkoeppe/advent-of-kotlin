package scaffold

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.int
import io.Filer
import java.awt.Desktop
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists

class Scaffolder : CliktCommand() {
  private val mainSources: Path = Paths.get("src/main/kotlin")
  private val testSources: Path = Paths.get("src/test/kotlin")
  private val client: AdventOfCodeClient = AdventOfCodeClient(Path.of(System.getProperty("user.home"), ".aoc"))

  private val year: Int by option().int().required().help("Year of the problem to setup.")
  private val day: Int by option().int().required().help("Day of the problem to setup.")
  private val openBrowser: Boolean by option().flag().help("Open the browser to the chosen problem.")
  private val force: Boolean by option().flag().help("Force regeneration of classes even if they already exist.")

  override fun run() {
    generateClassFiles()
    generateTestFiles()
    generateInputFiles()

    if (openBrowser) Desktop.getDesktop().browse(client.problemUrl(year, day))
  }

  private fun generateClassFiles() {
    val path = mainSources.resolve("y$year").resolve("Day$day.kt")
    if (path.exists() && !force) {
      println("Skipping $path as it already exists.")
      return
    }

    Filer.writeStringCreatingDirectories(path, renderTemplate("src/main/resources/Day.txt"))
  }

  private fun generateTestFiles() {
    val path = testSources.resolve("y$year").resolve("Day${day}Tests.kt")
    if (path.exists() && !force) {
      println("Skipping $path as it already exists.")
      return
    }

    Filer.writeStringCreatingDirectories(path, renderTemplate("src/main/resources/DayTests.txt"))
  }

  private fun renderTemplate(path: String): String {
    val templateReplacements = mapOf("{year}" to year, "{day}" to day)
    return templateReplacements.keys.fold(Files.readString(Path.of(path))) { t, k ->
      t.replace(k, templateReplacements[k]!!.toString())
    }
  }

  private fun generateInputFiles() {
    Filer.writeStringCreatingDirectories(
      Path.of("src/test/resources/$year/$day/problem.txt"),
      client.getProblemInput(year, day),
    )
    Filer.writeStringCreatingDirectories(Path.of("src/test/resources/$year/$day/example.txt"), "")
  }
}
