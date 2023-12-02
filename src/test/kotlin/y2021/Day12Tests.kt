package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day12.partOne(Filer.read(2021, 12, "example-1.txt"))).isEqualTo(10)
    assertThat(Day12.partOne(Filer.read(2021, 12, "example-2.txt"))).isEqualTo(19)
    assertThat(Day12.partOne(Filer.read(2021, 12, "example-3.txt"))).isEqualTo(226)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Filer.readProblem(2021, 12))).isEqualTo(4167)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Filer.read(2021, 12, "example-1.txt"))).isEqualTo(36)
    assertThat(Day12.partTwo(Filer.read(2021, 12, "example-2.txt"))).isEqualTo(103)
    assertThat(Day12.partTwo(Filer.read(2021, 12, "example-3.txt"))).isEqualTo(3509)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Filer.readProblem(2021, 12))).isEqualTo(98441)
  }
}
