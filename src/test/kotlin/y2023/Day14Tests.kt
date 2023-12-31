package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Tests {
  @Test
  fun partOneExample() {
    assertThat(Day14.partOne(Filer.readExample(2023, 14))).isEqualTo(136)
  }

  @Test
  fun partOne() {
    assertThat(Day14.partOne(Filer.readProblem(2023, 14))).isEqualTo(108614)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day14.partTwo(Filer.readExample(2023, 14))).isEqualTo(64)
  }

  @Test
  fun partTwo() {
    assertThat(Day14.partTwo(Filer.readProblem(2023, 14))).isEqualTo(96447)
  }
}
