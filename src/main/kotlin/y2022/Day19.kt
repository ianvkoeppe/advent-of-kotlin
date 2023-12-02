package y2022

import kotlin.math.ceil
import kotlin.math.max

object Day19 {
  enum class Resource {
    GEODE,
    OBSIDIAN,
    CLAY,
    ORE
  }

  private data class Blueprint(
    val costs: Map<Resource, Map<Resource, Int>>,
    val robots: Map<Resource, Int> = mapOf(Resource.ORE to 1),
    val resources: Map<Resource, Int> = mapOf()
  ) {
    fun findMaxCrackableGeodes(minutes: Int = 24, maxThusFar: Int = 0): Int {
      if (maxThusFar > findMaxPossibleRemainingGeodes(minutes)) return maxThusFar

      return findTimeToBuildRobots()
        .filterValues { minutes - it > 0 }
        .entries
        .fold(maxThusFar) { maxes, (robot, turns) ->
          val withRobot = mineResources(turns).build(robot)
          val maxIfCoasting = withRobot.mineResources(minutes - turns).numberOfGeodes()
          withRobot.findMaxCrackableGeodes(minutes - turns, max(maxes, maxIfCoasting))
        }
    }

    private fun mineResources(turns: Int = 1): Blueprint =
      copy(
        resources = robots.map { (robot, count) -> robot to resources.getOrDefault(robot, 0) + (turns * count) }.toMap()
      )

    private fun findTimeToBuildRobots(): Map<Resource, Int> =
      Resource.values()
        .filter(::shouldBuild)
        .map { robot -> robot to findTimeToBuildRobot(robot) }
        .filterNot { (_, time) -> time == null }
        .associate { (robot, time) -> robot to time!! }

    private fun findTimeToBuildRobot(robot: Resource): Int? {
      val costs = costs.getValue(robot)
      if (costs.any { (type, _) -> robots.getOrDefault(type, 0) == 0 }) return null

      return max(
        0,
        costs
          .maxOf { (type, cost) -> ceil((cost.toDouble() - resources.getOrDefault(type, 0)) / robots.getValue(type)) }
          .toInt()
      ) + 1
    }

    private fun shouldBuild(robot: Resource): Boolean =
      robot == Resource.GEODE || robots.getOrDefault(robot, 0) < costs.values.maxOf { it.getOrDefault(robot, 0) }

    private fun build(robot: Resource): Blueprint {
      val withRobot = robots + (robot to (robots[robot] ?: 0) + 1)
      val minusResources = resources.mapValues { (type, amount) -> amount - (costs[robot]?.get(type) ?: 0) }.toMap()
      return copy(robots = withRobot, resources = minusResources)
    }

    private fun findMaxPossibleRemainingGeodes(minutes: Int): Int =
      mineResources(minutes).numberOfGeodes() + (minutes * (minutes + 1) / 2)

    private fun numberOfGeodes(): Int = resources[Resource.GEODE] ?: 0
  }

  fun partOne(lines: List<String>): Int =
    parse(lines).map(Blueprint::findMaxCrackableGeodes).mapIndexed { i, max -> (i + 1) * max }.sum()

  fun partTwo(lines: List<String>): Int = parse(lines).take(3).map { it.findMaxCrackableGeodes(32) }.reduce(Int::times)

  private val blueprint = "Each ([a-z]+) robot costs (\\d+?) ([a-z]+)( and (\\d+) ([a-z]+))?.".toRegex()

  private fun parse(lines: List<String>): List<Blueprint> = lines.map(::parseBlueprint)

  private fun parseBlueprint(line: String): Blueprint {
    return Blueprint(
      blueprint
        .findAll(line)
        .map { match ->
          val (robotType, firstAmount, firstType) = match.groupValues.drop(1)
          val (secondAmount, secondType) = match.groupValues.drop(5)
          val costs =
            listOf(firstType to firstAmount, secondType to secondAmount)
              .filterNot { it.first == "" }
              .associate { (type, amount) -> Resource.valueOf(type.uppercase()) to amount.toInt() }
          Resource.valueOf(robotType.uppercase()) to costs
        }
        .toMap()
    )
  }
}
