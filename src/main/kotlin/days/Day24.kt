package days

data class Val(val id: String? = null, val primitive: Int? = null) { override fun toString(): String = id ?: primitive.toString() }
data class Op(val left: Expr, val op: String, val right: Expr?) {
  override fun toString(): String {
    if (op == "inp") return "read()"
    if (op == "eql") return "(if ($left == $right) 1 else 0)"
    val o = when(op) { "add" -> "+" "mul" -> "*" "div" -> "/" "mod" -> "%" else -> "" }
    return "($left $o $right)"
  }
}
data class Expr(val value: Val? = null, val op: Op? = null) { override fun toString(): String = value?.toString() ?: op.toString() }
data class Assignment(val variable: String, val op: Op) { override fun toString(): String = "$variable = $op" }

object Day24 {

  fun partOne(lines: List<String>): Long = solveBackwards(parseConstants(parse(lines))).first
  fun partTwo(lines: List<String>): Long = solveBackwards(parseConstants(parse(lines))).second

  private fun parse(lines: List<String>): List<Assignment> {
    return lines
      .map { line -> line.split(" ") }
      .map { parts ->
        val rightSideOfOperation = parts.getOrNull(2)
        val rightHandExpression = Expr(value = Val(id = rightSideOfOperation, primitive = rightSideOfOperation?.toIntOrNull()))
        Assignment(parts[1], Op(Expr(value = Val(id = parts[1])), parts[0], if (rightSideOfOperation != null) rightHandExpression else null))
      }
  }

  private fun parseConstants(program: List<Assignment>): List<Triple<Int, Int, Int>> {
    return program.mapIndexedNotNull { index, assignment ->
      if (assignment.op.op == "inp") Triple(parseRightHandPrimitive(program[index + 4]), parseRightHandPrimitive(program[index + 5]), parseRightHandPrimitive(program[index + 15])) else null
    }
  }
  private fun parseRightHandPrimitive(assignment: Assignment): Int = assignment.op.right?.value?.primitive!!

  private fun solveBackwards(constants: List<Triple<Int, Int, Int>>, digitPosition: Int = 13, targetZValues: List<Int> = listOf(0), targetZToDigit: Map<Int, Map<Int, List<Pair<Int, Int>>>> = mapOf()): Pair<Long, Long> {
    if (digitPosition < 0) return findNumberEqualingZByComparator(targetZToDigit, { -it.first }).toLong() to findNumberEqualingZByComparator(targetZToDigit, { it.first }).toLong()

    val nextTargetZValues = findCandidateDigits(constants[digitPosition], targetZValues)
    return solveBackwards(constants, digitPosition - 1, nextTargetZValues.keys.toList(), targetZToDigit + (digitPosition to nextTargetZValues))
  }

  private fun findCandidateDigits(constants: Triple<Int, Int, Int>, targetZValues: List<Int>): Map<Int, List<Pair<Int, Int>>> {
    return (1..9).reversed().map { candidateDigit ->
      targetZValues.flatMap { targetZValue ->
        (0 until constants.first).flatMap { A ->
          listOfNotNull(firstValidCheck(candidateDigit, targetZValue, constants, A), secondValidCheck(candidateDigit, targetZValue, constants, A))
        }.map { position -> position to (candidateDigit to targetZValue) }
      }.toMap()
    }.asSequence().flatMap { it.asSequence() }.groupBy({ it.key }, { it.value })
  }

  private fun firstValidCheck(candidateDigit: Int, targetZValue: Int, constants: Triple<Int, Int, Int>, A: Int): Int? {
    val position = targetZValue * constants.first + A
    return if (position % 26 + constants.second == candidateDigit && position / constants.first == targetZValue) position else null
  }

  private fun secondValidCheck(candidateDigit: Int, targetZValue: Int, constants: Triple<Int, Int, Int>, A: Int): Int? {
    val position = (targetZValue - candidateDigit - constants.third) / 26 * constants.first + A
    return if (position % 26 + constants.second != candidateDigit && position / constants.first * 26 + candidateDigit + constants.third == targetZValue) position else null
  }

  private fun findNumberEqualingZByComparator(targetZToDigit: Map<Int, Map<Int, List<Pair<Int, Int>>>>, comparator: (Pair<Int, Int>) -> Int, digitPosition: Int = 0, targetZ: Int = 0): String {
    if (digitPosition == 14) return ""
    val (digit, nextTargetZ) = targetZToDigit.getValue(digitPosition).getValue(targetZ).minByOrNull(comparator)!!
    return digit.toString() + findNumberEqualingZByComparator(targetZToDigit, comparator, digitPosition + 1, nextTargetZ)
  }

  /**
   * Provides expression showing groups appear in the form with only the constants a, b, c changing which appear at
   * indices 4, 5, and 14 from each input line...
   *
   * znext = (((z / a) * ((25 * ((((z % 26) + b) == read() ? 1 : 0) == 0 ? 1 : 0)) + 1)) + ((read() + c) * ((((z % 26) + b) == read() ? 1 : 0) == 0 ? 1 : 0)))
   *
   * ...which simplifies to...
   *
   * x = if ((z % 26) + b == w) 0 else 1 (x can only be 0 or 1)
   * znext = x * (z / a) * (25 * (z / a) + w + c)
   *             (z / a) * 25 + w + c) + (z / a)
   *
   * ...solving for z would allow us to step back through the equation starting with the lowest significant digit...
   *
   *  1) z = znext * a + A; for A in [0, a-1]
   *  2) z = (znext - w - c) / 26 * a + A; for A in [0, c-1]
   */
  private fun reverseEngineer(program: List<Assignment>, index: Int = program.size - 1, value: Val = Val(id = "z")): Expr {
    if (index < 0) return Expr(value)
    if (value.primitive != null) return Expr(value = value)

    val statement = program[index]
    if (statement.variable != value.id) return reverseEngineer(program, index - 1, value)

    val left = reverseEngineer(program, index - 1, statement.op.left.value!!)
    val right = if (statement.op.right != null) reverseEngineer(program, index - 1, statement.op.right.value!!) else null

    return optimize(Expr(op = Op(left, statement.op.op, right)))
  }

  private fun optimize(expr: Expr): Expr {
    return when (expr.op?.op) {
      "add" -> if (expr.op.left.value?.primitive == 0) expr.op.right!! else if (expr.op.right?.value?.primitive == 0) expr.op.left else expr
      "mul" -> {
        if (expr.op.left.value?.primitive == 1) expr.op.right!!
        else if (expr.op.right?.value?.primitive == 1) expr.op.left
        else if (expr.op.left.value?.primitive == 0 || expr.op.right?.value?.primitive == 0) Expr(value = Val(primitive = 0))
        else expr
      }
      "div" -> if (expr.op.right?.value?.primitive == 1) expr.op.left else expr
      else -> expr
    }
  }
}