package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Tests {
  @Test
  fun partOneExample() {
    assertThat(Day2.partOne(Filer.readExample(2023, 2))).isEqualTo(8)
  }

  @Test
  fun partOne() {
    assertThat(Day2.partOne(Filer.readProblem(2023, 2))).isEqualTo(2317)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day2.partTwo(Filer.readExample(2023, 2))).isEqualTo(2286)
  }

  @Test
  fun partTwo() {
    assertThat(Day2.partTwo(Filer.readProblem(2023, 2))).isEqualTo(74804)
  }
}
