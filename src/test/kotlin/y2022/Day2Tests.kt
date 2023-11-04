package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Tests {
  @Test
  fun partOneExample() {
    assertThat(Day2.partOne(Filer.readExample(2022, 2))).isEqualTo(15)
  }

  @Test
  fun partOne() {
    assertThat(Day2.partOne(Filer.readProblem(2022, 2))).isEqualTo(10404)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day2.partTwo(Filer.readExample(2022, 2))).isEqualTo(12)
  }

  @Test
  fun partTwo() {
    assertThat(Day2.partTwo(Filer.readProblem(2022, 2))).isEqualTo(10334)
  }
}
