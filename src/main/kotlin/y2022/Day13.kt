package y2022

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

object Day13 {
  enum class Comparison {
    LESS,
    GREATER,
    EQUAL
  }

  private val dividers = listOf(jsonArrayOf(jsonArrayOf(JsonPrimitive(2))), jsonArrayOf(jsonArrayOf(JsonPrimitive(6))))

  fun partOne(lines: List<String>): Int {
    return parse(lines)
      .asSequence()
      .chunked(2)
      .map { (f, s) -> Pair(f, s) }
      .mapIndexed { index, pair -> index to compareTo(pair.first, pair.second) }
      .filter { (_, rightOrder) -> rightOrder == Comparison.LESS }
      .sumOf { it.first + 1 }
  }

  fun partTwo(lines: List<String>): Int {
    val packets = parse(lines) + dividers
    val sorted = packets.sortedWith { f, s -> if (compareTo(f, s) == Comparison.LESS) -1 else 1 }
    return dividers.map { sorted.indexOf(it) + 1 }.reduce(Int::times)
  }

  private fun parse(lines: List<String>): List<JsonArray> {
    return lines.filter { it.isNotEmpty() }.map { line -> Gson().fromJson(line, JsonArray::class.java) }
  }

  private fun compareTo(f: JsonArray, s: JsonArray, index: Int = 0): Comparison {
    return when {
      index >= f.size() && index < s.size() -> Comparison.LESS
      index >= f.size() && index >= s.size() -> Comparison.EQUAL
      index < f.size() && index >= s.size() -> Comparison.GREATER
      else -> {
        val order = compareTo(f.get(index), s.get(index))
        if (order == Comparison.LESS || order == Comparison.GREATER) order else compareTo(f, s, index + 1)
      }
    }
  }

  private fun compareTo(f: JsonElement, s: JsonElement): Comparison {
    return when {
      f is JsonArray && s is JsonArray -> compareTo(f, s)
      f is JsonPrimitive && s is JsonPrimitive -> compareTo(f, s)
      f is JsonArray && s is JsonPrimitive -> compareTo(f, jsonArrayOf(s))
      f is JsonPrimitive && s is JsonArray -> compareTo(jsonArrayOf(f), s)
      else -> Comparison.EQUAL
    }
  }

  private fun compareTo(f: JsonPrimitive, s: JsonPrimitive): Comparison =
    when {
      f.asInt < s.asInt -> Comparison.LESS
      f.asInt == s.asInt -> Comparison.EQUAL
      else -> Comparison.GREATER
    }

  private fun jsonArrayOf(elem: JsonElement): JsonArray = JsonArray(1).also { it.add(elem) }
}
