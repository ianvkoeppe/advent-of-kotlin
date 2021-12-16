package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day12.partOne(Reader.read("src/test/resources/day12/example-1.txt"))).isEqualTo(10)
    assertThat(Day12.partOne(Reader.read("src/test/resources/day12/example-2.txt"))).isEqualTo(19)
    assertThat(Day12.partOne(Reader.read("src/test/resources/day12/example-3.txt"))).isEqualTo(226)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Reader.read("src/test/resources/day12/problem.txt"))).isEqualTo(4167)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Reader.read("src/test/resources/day12/example-1.txt"))).isEqualTo(36)
    assertThat(Day12.partTwo(Reader.read("src/test/resources/day12/example-2.txt"))).isEqualTo(103)
    assertThat(Day12.partTwo(Reader.read("src/test/resources/day12/example-3.txt"))).isEqualTo(3509)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Reader.read("src/test/resources/day12/problem.txt"))).isEqualTo(98441)
  }
}