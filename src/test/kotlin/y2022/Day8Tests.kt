package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Tests {
  @Test
  fun partOneExample() {
    assertThat(Day8.partOne(Filer.readExample(2022, 8))).isEqualTo(21)
  }

  @Test
  fun partOne() {
    assertThat(Day8.partOne(Filer.readProblem(2022, 8))).isEqualTo(1698)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day8.partTwo(Filer.readExample(2022, 8))).isEqualTo(8)
  }

  @Test
  fun partTwo() {
    assertThat(Day8.partTwo(Filer.readProblem(2022, 8))).isEqualTo(672280)
  }
}
