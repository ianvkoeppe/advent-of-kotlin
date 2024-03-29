package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {
  @Test
  fun partOneExample() {
    assertThat(Day6.partOne(Filer.readExample(2021, 6))).isEqualTo(5934)
  }

  @Test
  fun partOne() {
    assertThat(Day6.partOne(Filer.readProblem(2021, 6))).isEqualTo(362346)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day6.partTwo(Filer.readExample(2021, 6))).isEqualTo(26984457539)
  }

  @Test
  fun partTwo() {
    assertThat(Day6.partTwo(Filer.readProblem(2021, 6))).isEqualTo(1639643057051)
  }
}
