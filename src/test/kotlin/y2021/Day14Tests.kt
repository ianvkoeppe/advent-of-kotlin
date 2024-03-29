package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day14.partOne(Filer.readExample(2021, 14))).isEqualTo(1588)
  }

  @Test
  fun partOne() {
    assertThat(Day14.partOne(Filer.readProblem(2021, 14))).isEqualTo(3831)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day14.partTwo(Filer.readExample(2021, 14))).isEqualTo(2188189693529L)
  }

  @Test
  fun partTwo() {
    assertThat(Day14.partTwo(Filer.readProblem(2021, 14))).isEqualTo(5725739914282L)
  }
}
