package y2023

object Day19 {
  private data class Rule(val category: String, val range: LongRange, val destination: String) {
    companion object {
      val maxRange: LongRange = 1L..4000L

      fun of(category: String, operation: String, amount: Long, destination: String): Rule {
        return Rule(
          category,
          if (operation == "<") (maxRange.first ..< amount) else ((amount + 1)..maxRange.last),
          destination
        )
      }
    }

    fun negate(): Rule =
      copy(
        range = if (range.first == maxRange.first) (range.last + 1)..maxRange.last else maxRange.first ..< range.first
      )

    fun constrain(configuration: AcceptableConfiguration): AcceptableConfiguration {
      val currentRange = configuration.categories.getValue(category)
      val newRange = currentRange.first.coerceAtLeast(range.first)..currentRange.last.coerceAtMost(range.last)
      return configuration.copy(categories = configuration.categories + (category to newRange))
    }
  }

  private data class Workflow(val rules: List<Rule>, val default: String) {
    fun getBranches(): List<List<Rule>> {
      val rulesBranches =
        rules.indices
          .map { i -> rules.slice(0..i) }
          .map { branch -> branch.dropLast(1).map(Rule::negate) + branch.takeLast(1) }
      val defaultBranch =
        rules.map(Rule::negate).let { rules -> rules.dropLast(1) + rules.last().copy(destination = default) }
      return rulesBranches + listOf(defaultBranch)
    }
  }

  private data class Part(val categories: Map<String, Int>) {
    fun rating(): Int = categories.values.sum()
  }

  private data class AcceptableConfiguration(
    val categories: Map<String, LongRange> = listOf("x", "m", "a", "s").associateWith { Rule.maxRange }
  ) {
    fun isAcceptable(part: Part): Boolean =
      categories.all { (category, range) ->
        part.categories.getValue(category) >= range.first && part.categories.getValue(category) <= range.last
      }

    fun maxPossibleValues(): Long = categories.values.map(LongRange::count).map(Int::toLong).reduce(Long::times)
  }

  private class PartsAvalanche(lines: List<String>) {
    private val inputs: List<String> = lines.joinToString("\n").split("\n\n")
    private val workflows: Map<String, Workflow> =
      inputs.first().split("\n").associate { line ->
        val (name, rulesSection) = "(.+)\\{(.+)}".toRegex().find(line)!!.groupValues.drop(1)
        val rulesDefinitions = rulesSection.split(",")
        val allRules =
          rulesDefinitions.dropLast(1).map { rule ->
            val (category, operation, amount, destination) =
              "(.)([<>])(\\d+):(.+)".toRegex().find(rule)!!.groupValues.drop(1)
            Rule.of(category, operation, amount.toLong(), destination)
          }
        name to Workflow(allRules, rulesDefinitions.last())
      }
    private val parts: List<Part> =
      inputs.last().split("\n").map { line ->
        val categories =
          line.trimStart('{').trimEnd('}').split(",").associate { category ->
            val (name, amount) = category.split("=")
            name to amount.toInt()
          }
        Part(categories)
      }
    private val acceptableConfigurations: List<AcceptableConfiguration> = findAllAcceptableConfigurations()

    fun countAcceptableParts(): Int =
      parts.filter { part -> acceptableConfigurations.any { it.isAcceptable(part) } }.sumOf(Part::rating)

    fun countAllPossibleAcceptableParts(): Long =
      acceptableConfigurations.sumOf(AcceptableConfiguration::maxPossibleValues)

    fun findAllAcceptableConfigurations(
      workflowName: String = "in",
      configuration: AcceptableConfiguration = AcceptableConfiguration()
    ): List<AcceptableConfiguration> {
      if (workflowName == "A") return listOf(configuration)
      if (workflowName == "R") return listOf()

      val workflow = workflows.getValue(workflowName)
      return workflow.getBranches().flatMap { branch ->
        val ranges = branch.fold(configuration) { configuration, rule -> rule.constrain(configuration) }
        findAllAcceptableConfigurations(branch.last().destination, ranges)
      }
    }
  }

  fun partOne(lines: List<String>): Int = PartsAvalanche(lines).countAcceptableParts()

  fun partTwo(lines: List<String>): Long = PartsAvalanche(lines).countAllPossibleAcceptableParts()
}
