package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExample() {
    assertThat(Day12.partOne(Filer.readExample(2023, 12))).isEqualTo(21)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Filer.readProblem(2023, 12))).isEqualTo(7407)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Filer.readExample(2023, 12))).isEqualTo(525152)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Filer.readProblem(2023, 12))).isEqualTo(30568243604962)
  }
}
