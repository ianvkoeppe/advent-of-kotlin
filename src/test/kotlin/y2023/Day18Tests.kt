package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Tests {
  @Test
  fun partOneExample() {
    assertThat(Day18.partOne(Filer.readExample(2023, 18))).isEqualTo(62)
  }

  @Test
  fun partOne() {
    assertThat(Day18.partOne(Filer.readProblem(2023, 18))).isEqualTo(52231)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day18.partTwo(Filer.readExample(2023, 18))).isEqualTo(952408144115)
  }

  @Test
  fun partTwo() {
    assertThat(Day18.partTwo(Filer.readProblem(2023, 18))).isEqualTo(57196493937398)
  }
}
