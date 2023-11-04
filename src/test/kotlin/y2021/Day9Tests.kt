package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Tests {
  @Test
  fun partOneExample() {
    assertThat(Day9.partOne(Filer.readExample(2021, 9))).isEqualTo(15)
  }

  @Test
  fun partOne() {
    assertThat(Day9.partOne(Filer.readProblem(2021, 9))).isEqualTo(550)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day9.partTwo(Filer.readExample(2021, 9))).isEqualTo(1134)
  }

  @Test
  fun partTwo() {
    assertThat(Day9.partTwo(Filer.readProblem(2021, 9))).isEqualTo(1100682)
  }
}
