package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day22Tests {
  @Test
  fun partOneExample() {
    assertThat(Day22.partOne(Filer.readExample(2023, 22))).isEqualTo(5)
  }

  @Test
  fun partOne() {
    assertThat(Day22.partOne(Filer.readProblem(2023, 22))).isEqualTo(428)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day22.partTwo(Filer.readExample(2023, 22))).isEqualTo(7)
  }

  @Test
  fun partTwo() {
    assertThat(Day22.partTwo(Filer.readProblem(2023, 22))).isEqualTo(35654)
  }
}
