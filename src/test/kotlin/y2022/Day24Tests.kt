package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day24Tests {
  @Test
  fun partOneExample() {
    assertThat(Day24.partOne(Filer.readExample(2022, 24))).isEqualTo(18)
  }

  @Test
  fun partOne() {
    assertThat(Day24.partOne(Filer.readProblem(2022, 24))).isEqualTo(343)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day24.partTwo(Filer.readExample(2022, 24))).isEqualTo(54)
  }

  @Test
  fun partTwo() {
    assertThat(Day24.partTwo(Filer.readProblem(2022, 24))).isEqualTo(960)
  }
}
