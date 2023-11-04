package y2022

object Day10 {
  data class Instruction(val cmd: String, val amount: Int? = null)

  data class State(
    private val register: List<Int> = listOf(1),
    private val cycle: Int = 0,
    private val screen: List<Char> = listOf()
  ) {
    private val screenSize = 40

    fun getValue(cycle: Int = this.cycle): Int = register[cycle]

    fun update(value: Int): State =
      copy(register = register + value, cycle = cycle + 1, screen = screen + if (shouldDraw()) '#' else ' ')

    private fun shouldDraw(): Boolean = getValue() - 1 <= cycle % screenSize && cycle % screenSize <= getValue() + 1

    override fun toString(): String = screen.chunked(screenSize).joinToString("\n") { it.joinToString("") }
  }

  fun partOne(lines: List<String>): Int {
    val state = parse(lines).fold(State()) { state, instruction -> step(state, instruction) }
    return (20..220 step 40).sumOf { it * state.getValue(it - 1) }
  }

  fun partTwo(lines: List<String>): String {
    val state = parse(lines).fold(State()) { state, instruction -> step(state, instruction) }
    return state.toString()
  }

  private fun parse(lines: List<String>): List<Instruction> {
    return lines.map {
      val parts = it.split(" ")
      if (parts.size > 1) Instruction(parts[0], parts[1].toInt()) else Instruction(parts[0])
    }
  }

  private fun step(state: State, instruction: Instruction): State {
    val next = state.update(state.getValue())
    return when (instruction.cmd) {
      "addx" -> next.update(next.getValue() + (instruction.amount ?: 0))
      else -> next
    }
  }
}
