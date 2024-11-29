package y2023

object Day20 {
  private data class Pulse(val sender: String, val high: Boolean, val destinations: List<String>)

  private abstract class Module(open val name: String, open val destinations: List<String>) {
    companion object {
      fun parse(lines: List<String>): Map<String, Module> {
        val modules = lines.associate(Module::parse)
        val conjunctions =
          modules.values.filterIsInstance<ConjunctionModule>().map { conjunction ->
            val sources =
              modules.values
                .filter { conjunction.name in it.destinations }
                .associate { it.name to false }
                .toMutableMap()
            conjunction.copy(previous = sources)
          }
        return modules + conjunctions.associateBy(ConjunctionModule::name)
      }

      private fun parse(line: String): Pair<String, Module> {
        val (module, destinationDefinitions) = line.split(" -> ")
        val destinations = destinationDefinitions.split(", ")
        val name = module.trimStart('%').trimStart('&')
        return name to
          when {
            module.startsWith("%") -> FlipFlopModule(name, destinations)
            module.startsWith("&") -> ConjunctionModule(name, destinations)
            else -> BroadcastModule(name, destinations)
          }
      }
    }

    abstract fun receive(sender: String, high: Boolean): Pulse
  }

  private data class BroadcastModule(override val name: String, override val destinations: List<String>) :
    Module(name, destinations) {
    override fun receive(sender: String, high: Boolean): Pulse = Pulse(name, high, destinations)
  }

  private data class FlipFlopModule(
    override val name: String,
    override val destinations: List<String>,
    var state: Boolean = false,
  ) : Module(name, destinations) {
    override fun receive(sender: String, high: Boolean): Pulse {
      state = if (high) state else !state
      return Pulse(name, state, if (high) listOf() else destinations)
    }
  }

  private data class ConjunctionModule(
    override val name: String,
    override val destinations: List<String>,
    val previous: MutableMap<String, Boolean> = mutableMapOf(),
  ) : Module(name, destinations) {
    override fun receive(sender: String, high: Boolean): Pulse {
      previous[sender] = high
      return Pulse(name, !previous.values.all { it }, destinations)
    }
  }

  private data class Stats(var buttonPresses: Int = 0, val pulses: MutableMap<Boolean, Int> = mutableMapOf()) {
    fun pulses(): Int = pulses.values.reduce(Int::times)
  }

  private class DesertMachineHeadquarters(lines: List<String>) {
    private val modules: Map<String, Module> = Module.parse(lines)

    fun sendPulses(times: Int = 1000): Stats = pressButtonUntil { stats, _ -> stats.buttonPresses == times }

    fun countButtonPressesToSendLowPulseToRX(): Long {
      // "rx" only has conjunction modules as inputs so find LCD of the time to send high from each.
      val rxSource = modules.filterValues { "rx" in it.destinations }
      val upstream = modules.filterValues { module -> rxSource.keys.any { it in module.destinations } }
      return upstream.keys.map(::countStepsToSendHighPulseFrom).map(Int::toLong).reduce(Long::times)
    }

    private fun countStepsToSendHighPulseFrom(module: String): Int {
      val pulses = mutableListOf<Int>()
      pressButtonUntil { stats, pulse ->
        pulses.also { if (pulse.sender == module && pulse.high) pulses.add(stats.buttonPresses) }.size == 2
      }
      return pulses.last() - pulses.first()
    }

    fun pressButtonUntil(terminatingCondition: (Stats, Pulse) -> Boolean): Stats {
      val stats = Stats()

      var terminate = false
      while (!terminate) {
        val queue = ArrayDeque(listOf(Pulse("button", false, listOf("broadcaster"))))
        stats.buttonPresses += 1

        while (queue.isNotEmpty()) {
          val pulse = queue.removeFirst()
          val pulses =
            pulse.destinations
              .filter(modules::containsKey)
              .map { moduleName -> modules.getValue(moduleName).receive(pulse.sender, pulse.high) }
              .filter { it.destinations.isNotEmpty() }
          queue.addAll(pulses)

          stats.pulses[pulse.high] = (stats.pulses[pulse.high] ?: 0) + pulse.destinations.size
          terminate = terminate || terminatingCondition(stats, pulse)
        }
      }

      return stats
    }
  }

  fun partOne(lines: List<String>): Int = DesertMachineHeadquarters(lines).sendPulses().pulses()

  fun partTwo(lines: List<String>): Long = DesertMachineHeadquarters(lines).countButtonPressesToSendLowPulseToRX()
}
