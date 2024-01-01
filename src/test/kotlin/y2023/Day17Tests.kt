package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day17Tests {
  @Test
  fun partOneExample() {
    assertThat(Day17.partOne(Filer.readExample(2023, 17))).isEqualTo(102)
  }

  @Test
  fun partOne() {
    assertThat(Day17.partOne(Filer.readProblem(2023, 17))).isEqualTo(758)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day17.partTwo(Filer.readExample(2023, 17))).isEqualTo(94)
  }

  @Test
  fun partTwo() {
    assertThat(Day17.partTwo(Filer.readProblem(2023, 17))).isEqualTo(892)
  }
}
