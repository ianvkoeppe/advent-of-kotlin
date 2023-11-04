package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Tests {
  @Test
  fun partOneExample() {
    assertThat(Day4.partOne(Filer.readExample(2022, 4))).isEqualTo(2)
  }

  @Test
  fun partOne() {
    assertThat(Day4.partOne(Filer.readProblem(2022, 4))).isEqualTo(448)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day4.partTwo(Filer.readExample(2022, 4))).isEqualTo(4)
  }

  @Test
  fun partTwo() {
    assertThat(Day4.partTwo(Filer.readProblem(2022, 4))).isEqualTo(794)
  }
}
