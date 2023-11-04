package scaffold

import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import okhttp3.OkHttpClient
import okhttp3.Request

class AdventOfCodeClient(sessionPath: Path) {
  private val domain: URI = URI.create("https://www.adventofcode.com")

  private val session: String = Files.readString(sessionPath).trim()
  private val client: OkHttpClient = OkHttpClient()

  private fun problemInputUrl(year: Int, day: Int): URI = URI.create("${problemUrl(year, day)}/input")

  fun problemUrl(year: Int, day: Int): URI = URI.create("$domain/$year/day/$day")

  fun getProblemInput(year: Int, day: Int): String = call(problemInputUrl(year, day))

  private fun call(uri: URI): String {
    val request = Request.Builder().url(uri.toURL()).addHeader("Cookie", "session=$session").build()
    return client.newCall(request).execute().body.string()
  }
}
