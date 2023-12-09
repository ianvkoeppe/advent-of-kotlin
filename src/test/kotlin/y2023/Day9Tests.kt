package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Tests {
  @Test
  fun partOneExample() {
    assertThat(Day9.partOne(Filer.readExample(2023, 9))).isEqualTo(114)
  }

  @Test
  fun partOne() {
    assertThat(Day9.partOne(Filer.readProblem(2023, 9))).isEqualTo(1782868781)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day9.partTwo(Filer.readExample(2023, 9))).isEqualTo(2)
  }

  @Test
  fun partTwo() {
    assertThat(Day9.partTwo(Filer.readProblem(2023, 9))).isEqualTo(1057)
  }
}
