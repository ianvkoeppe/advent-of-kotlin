package y2022

object Day11 {
  data class Test(val divisor: Int, private val trueMonkey: Int, private val falseMonkey: Int) {
    fun findMonkeyToPassTo(value: Long): Int = if (value % divisor == 0L) trueMonkey else falseMonkey
  }

  data class Monkey(val number: Int, val items: List<Long>, val operation: (Long) -> Long, val test: Test, val inspections: Long = 0) {
    fun operate(item: Long, stressManagementTechnique: (Long) -> Long): Long = stressManagementTechnique(operation(item))
    fun findMonkeyToPassTo(item: Long): Int = test.findMonkeyToPassTo(item)
  }

  data class Troop(private val monkeys: List<Monkey>) {
    fun monkeyAround(rounds: Int, stressManagementTechnique: (Long) -> Long): Troop {
      if (rounds == 0) return this

      return monkeys.indices.fold(this) { troop, monkey ->
        troop.monkeyAround(troop.monkeys[monkey], stressManagementTechnique)
      }.monkeyAround(rounds - 1, stressManagementTechnique)
    }

    private fun monkeyAround(current: Monkey, stressManagementTechnique: (Long) -> Long): Troop {
      val toPass = current.items.map { item -> current.operate(item, stressManagementTechnique) }.groupBy { item -> current.findMonkeyToPassTo(item) }
      val monkeys = monkeys.map { monkey ->
        monkey.copy(
          items = if (monkey.number == current.number) listOf() else monkey.items + (toPass[monkey.number] ?: listOf()),
          inspections = monkey.inspections + if (monkey.number == current.number) current.items.size else 0
        )
      }
      return Troop(monkeys)
    }

    fun findProductOfTestValues(): Int = monkeys.map { it.test.divisor }.reduce(Int::times)
    fun findMonkeyBusiness(): Long = monkeys.map { it.inspections }.sortedDescending().take(2).reduce(Long::times)
  }

  private val operations = mapOf<String, (Long, Long) -> Long>("*" to Long::times, "+" to Long::plus)

  fun partOne(lines: List<String>): Long = parseMonkeys(lines).monkeyAround(20) { item -> item / 3 }.findMonkeyBusiness()
  fun partTwo(lines: List<String>): Long {
    val troop = parseMonkeys(lines)
    return parseMonkeys(lines).monkeyAround(10000) { item -> item % troop.findProductOfTestValues() }.findMonkeyBusiness()
  }

  private fun parseMonkeys(lines: List<String>): Troop {
    return Troop(lines.joinToString("\n").split("\n\n").mapIndexed { number, monkey -> parseMonkey(monkey, number) })
  }
  private fun parseMonkey(line: String, number: Int): Monkey {
    val (items, operation, testCase, trueCase, falseCase) = line.split("\n").drop(1)
    return Monkey(number, parseItems(items), parseOperation(operation), parseTest(testCase, trueCase, falseCase))
  }
  private fun parseItems(line: String): List<Long> {
    val items = line.trim().removePrefix("Starting items: ").split(", ")
    return items.map { it.toLong() }
  }
  private fun parseOperation(line: String): (Long) -> Long {
    val (op, amount) = line.trim().removePrefix("Operation: new = old ").split(" ")
    return { value: Long ->
      val toApply = if (amount == "old") value else amount.toLong()
      operations.getValue(op)(value, toApply)
    }
  }
  private fun parseTest(testCase: String, trueCase: String, falseCase: String): Test {
    val divisor = testCase.trim().removePrefix("Test: divisible by ").toInt()
    val trueMonkey = trueCase.trim().removePrefix("If true: throw to monkey ").toInt()
    val falseMonkey = falseCase.trim().removePrefix("If false: throw to monkey ").toInt()
    return Test(divisor, trueMonkey, falseMonkey)
  }
}