package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day12.partOne(Reader.read("example-1.txt", 2021, 12))).isEqualTo(10)
    assertThat(Day12.partOne(Reader.read("example-2.txt", 2021, 12))).isEqualTo(19)
    assertThat(Day12.partOne(Reader.read("example-3.txt", 2021, 12))).isEqualTo(226)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Reader.readProblem(2021, 12))).isEqualTo(4167)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Reader.read("example-1.txt", 2021, 12))).isEqualTo(36)
    assertThat(Day12.partTwo(Reader.read("example-2.txt", 2021, 12))).isEqualTo(103)
    assertThat(Day12.partTwo(Reader.read("example-3.txt", 2021, 12))).isEqualTo(3509)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Reader.readProblem(2021, 12))).isEqualTo(98441)
  }
}