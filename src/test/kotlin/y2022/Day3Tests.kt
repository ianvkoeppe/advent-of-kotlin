package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Tests {
  @Test
  fun partOneExample() {
    assertThat(Day3.partOne(Filer.readExample(2022, 3))).isEqualTo(157)
  }

  @Test
  fun partOne() {
    assertThat(Day3.partOne(Filer.readProblem(2022, 3))).isEqualTo(8202)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day3.partTwo(Filer.readExample(2022, 3))).isEqualTo(70)
  }

  @Test
  fun partTwo() {
    assertThat(Day3.partTwo(Filer.readProblem(2022, 3))).isEqualTo(2864)
  }
}
