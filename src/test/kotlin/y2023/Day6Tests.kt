package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {
  @Test
  fun partOneExample() {
    assertThat(Day6.partOne(Filer.readExample(2023, 6))).isEqualTo(288)
  }

  @Test
  fun partOne() {
    assertThat(Day6.partOne(Filer.readProblem(2023, 6))).isEqualTo(840336)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day6.partTwo(Filer.readExample(2023, 6))).isEqualTo(71503)
  }

  @Test
  fun partTwo() {
    assertThat(Day6.partTwo(Filer.readProblem(2023, 6))).isEqualTo(41382569)
  }
}
