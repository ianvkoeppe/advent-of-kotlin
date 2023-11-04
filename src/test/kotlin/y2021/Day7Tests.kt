package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Tests {
  @Test
  fun partOneExample() {
    assertThat(Day7.partOne(Filer.readExample(2021, 7))).isEqualTo(37)
  }

  @Test
  fun partOne() {
    assertThat(Day7.partOne(Filer.readProblem(2021, 7))).isEqualTo(340987)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day7.partTwo(Filer.readExample(2021, 7))).isEqualTo(168)
  }

  @Test
  fun partTwo() {
    assertThat(Day7.partTwo(Filer.readProblem(2021, 7))).isEqualTo(96987874)
  }
}
