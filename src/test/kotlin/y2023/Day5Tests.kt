package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Tests {
  @Test
  fun partOneExample() {
    assertThat(Day5.partOne(Filer.readExample(2023, 5))).isEqualTo(35)
  }

  @Test
  fun partOne() {
    assertThat(Day5.partOne(Filer.readProblem(2023, 5))).isEqualTo(107430936)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day5.partTwo(Filer.readExample(2023, 5))).isEqualTo(46)
  }

  @Test
  fun partTwo() {
    assertThat(Day5.partTwo(Filer.readProblem(2023, 5))).isEqualTo(23738616)
  }
}
