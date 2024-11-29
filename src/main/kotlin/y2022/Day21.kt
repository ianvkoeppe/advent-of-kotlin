package y2022

object Day21 {
  private data class Expression(val id: String? = null, val value: Long? = null, val operation: Operation? = null) {
    fun canSolve(): Boolean = id == null && (operation?.canSolve() ?: true)

    fun eval(): Long = value ?: operation!!.eval()

    fun evalOtherSide(otherSide: Long): Long {
      val solved =
        if (operation == null) otherSide
        else {
          val solved = findSolvableSide()
          when (operation.op) {
            "+" -> otherSide - solved
            "-" -> if (operation.lhs.canSolve()) solved - otherSide else otherSide + solved
            "*" -> otherSide / solved
            else -> if (operation.lhs.canSolve()) solved / otherSide else otherSide * solved
          }
        }
      return findUnsolvableExpression()?.evalOtherSide(solved) ?: solved
    }

    fun findSolvableSide(): Long = if (operation!!.lhs.canSolve()) operation.lhs.eval() else operation.rhs.eval()

    fun findUnsolvableExpression(): Expression? =
      if (operation?.lhs?.canSolve() == true) operation.rhs else operation?.lhs
  }

  private data class Operation(val lhs: Expression, val op: String, val rhs: Expression) {
    private val operations: Map<String, (Long, Long) -> Long> =
      mapOf("+" to Long::plus, "-" to Long::minus, "*" to Long::times, "/" to Long::div)

    fun canSolve(): Boolean = lhs.canSolve() && rhs.canSolve()

    fun eval(): Long = operations.getValue(op)(lhs.eval(), rhs.eval())
  }

  private data class Troop(val monkeys: Map<String, Expression>) {
    fun findValue(monkey: String): Long = resolveExpressions(monkey).eval()

    fun solveEquality(expression: Expression = resolveExpressions("root")): Long {
      return expression.findUnsolvableExpression()?.evalOtherSide(expression.findSolvableSide()) ?: 0
    }

    private fun resolveExpressions(id: String): Expression {
      val monkey = monkeys[id]
      return when {
        monkey == null -> return Expression(id = id)
        monkey.id != null -> return resolveExpressions(monkey.id)
        monkey.value != null -> return Expression(value = monkey.value)
        else ->
          Expression(
            operation =
              Operation(
                resolveExpressions(monkey.operation!!.lhs.id!!),
                monkey.operation.op,
                resolveExpressions(monkey.operation.rhs.id!!),
              )
          )
      }
    }
  }

  fun partOne(lines: List<String>): Long = Troop(parse(lines)).findValue("root")

  fun partTwo(lines: List<String>): Long = Troop(parse(lines) + ("humn" to Expression("x"))).solveEquality()

  private val operation = "([a-z]+): ([a-z]+) ([+\\-*/]) ([a-z]+)".toRegex()
  private val number = "([a-z]+): (\\d+)".toRegex()

  private fun parse(lines: List<String>): Map<String, Expression> = lines.associate(::parse)

  private fun parse(line: String): Pair<String, Expression> =
    when {
      line.matches(operation) -> {
        val (id, lhs, op, rhs) = operation.find(line)!!.groupValues.drop(1)
        id to Expression(operation = Operation(Expression(id = lhs), op, Expression(rhs)))
      }
      else -> {
        val (id, number) = number.find(line)!!.groupValues.drop(1)
        id to Expression(value = number.toLong())
      }
    }
}
