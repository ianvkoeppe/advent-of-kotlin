package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Tests {
  @Test
  fun partOneExample() {
    assertThat(Day4.partOne(Filer.readExample(2023, 4))).isEqualTo(13)
  }

  @Test
  fun partOne() {
    assertThat(Day4.partOne(Filer.readProblem(2023, 4))).isEqualTo(23235)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day4.partTwo(Filer.readExample(2023, 4))).isEqualTo(30)
  }

  @Test
  fun partTwo() {
    assertThat(Day4.partTwo(Filer.readProblem(2023, 4))).isEqualTo(5920640)
  }
}
