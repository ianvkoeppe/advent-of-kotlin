package y2021

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import kotlin.math.ceil

object Day18 {

  fun partOne(lines: List<String>): Int {
    return calculate(lines.map(::parse))
  }

  fun partTwo(lines: List<String>): Int {
    val nums = lines.map(::parse)
    return nums.maxOf { left -> nums.filter { it != left }.maxOf { right -> calculate(listOf(left, right)) } }
  }

  internal fun parse(json: String): JsonArray {
    return Gson().fromJson(json, JsonArray::class.java)
  }

  internal fun flatten(fishNum: JsonArray, depth: Int = 0): List<Pair<Int, Int>> {
    val left = if (fishNum.first().isJsonPrimitive) listOf(fishNum.first().asInt to depth) else flatten(fishNum.first().asJsonArray, depth + 1)
    val right = if (fishNum.last().isJsonPrimitive) listOf(fishNum.last().asInt to depth) else flatten(fishNum.last().asJsonArray, depth + 1)
    return left + right
  }

  internal fun nest(fishNums: List<Pair<Int, Int>>): JsonArray {
    val stack = ArrayDeque<Pair<String, Int>>()
    fishNums.forEach { numToDepth ->
      stack.add(numToDepth.first.toString() to numToDepth.second)

      while (stack.size > 1 && stack[stack.size - 1].second == stack[stack.size - 2].second) {
        val right = stack.removeLast()
        val left = stack.removeLast()
        stack.add("[${left.first},${right.first}]" to right.second - 1)
      }
    }
    return parse(stack.joinToString("") { it.first })
  }

  private fun calculate(fishNums: List<JsonArray>): Int {
    return magnitude(fishNums.reduce { left, right -> nest(explodeAndSplit(flatten(newJsonArray(left, right)))) })
  }

  internal fun explodeAndSplit(numsToDepths: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val updated = numsToDepths.toMutableList()

    val indexToExplode = numsToDepths.zipWithNext().indexOfFirst { (first, second) -> first.second >= 4 && first.second == second.second }
    if (indexToExplode != -1) {
      if (indexToExplode - 1 >= 0) {
        val valToLeft = updated[indexToExplode - 1]
        updated[indexToExplode - 1] = updated[indexToExplode].first + valToLeft.first to valToLeft.second
      }
      if (indexToExplode + 2 < updated.size) {
        val valToRight = updated[indexToExplode + 2]
        updated[indexToExplode + 2] = updated[indexToExplode + 1].first + valToRight.first to valToRight.second
      }

      updated[indexToExplode] = (0 to updated[indexToExplode].second - 1)
      updated.removeAt(indexToExplode + 1)
    } else {
      val indexToSplit = numsToDepths.indexOfFirst { it.first >= 10 }

      if (indexToSplit != -1) {
        updated.add(indexToSplit + 1, ceil(updated[indexToSplit].first / 2.0).toInt() to updated[indexToSplit].second + 1)
        updated[indexToSplit] = updated[indexToSplit].first / 2 to updated[indexToSplit].second + 1
      } else return numsToDepths
    }

    return explodeAndSplit(updated)
  }

  internal fun magnitude(fishNum: JsonArray): Int {
    val left = 3 * if (fishNum.first().isJsonPrimitive) fishNum.first().asInt else magnitude(fishNum.first().asJsonArray)
    val right = 2 * if (fishNum.last().isJsonPrimitive) fishNum.last().asInt else magnitude(fishNum.last().asJsonArray)
    return left + right
  }

  private fun newJsonArray(vararg elements: JsonElement): JsonArray {
    val array = JsonArray()
    elements.forEach(array::add)
    return array
  }
}