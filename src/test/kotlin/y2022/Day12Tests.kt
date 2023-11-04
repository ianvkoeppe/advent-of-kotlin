package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExample() {
    assertThat(Day12.partOne(Filer.readExample(2022, 12))).isEqualTo(31)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Filer.readProblem(2022, 12))).isEqualTo(468)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Filer.readExample(2022, 12))).isEqualTo(29)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Filer.readProblem(2022, 12))).isEqualTo(459)
  }
}
