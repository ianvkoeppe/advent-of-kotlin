package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Tests {
  @Test
  fun partOneExample() {
    assertThat(Day18.partOne(Filer.readExample(2022, 18))).isEqualTo(64)
  }

  @Test
  fun partOne() {
    assertThat(Day18.partOne(Filer.readProblem(2022, 18))).isEqualTo(3496)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day18.partTwo(Filer.readExample(2022, 18))).isEqualTo(58)
  }

  @Test
  fun partTwo() {
    assertThat(Day18.partTwo(Filer.readProblem(2022, 18))).isEqualTo(2064)
  }
}
