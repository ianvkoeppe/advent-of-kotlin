package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Tests {
  @Test
  fun partOneExample() {
    assertThat(Day9.partOne(Filer.read(2022, 9, "example-1.txt"))).isEqualTo(13)
  }

  @Test
  fun partOne() {
    assertThat(Day9.partOne(Filer.readProblem(2022, 9))).isEqualTo(5695)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day9.partTwo(Filer.read(2022, 9, "example-1.txt"))).isEqualTo(1)
    assertThat(Day9.partTwo(Filer.read(2022, 9, "example-2.txt"))).isEqualTo(36)
  }

  @Test
  fun partTwo() {
    assertThat(Day9.partTwo(Filer.readProblem(2022, 9))).isEqualTo(2434)
  }
}
