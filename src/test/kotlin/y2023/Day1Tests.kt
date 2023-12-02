package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Filer.read(2023, 1, "example-1.txt"))).isEqualTo(142)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Filer.readProblem(2023, 1))).isEqualTo(54601)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Filer.read(2023, 1, "example-2.txt"))).isEqualTo(281)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Filer.readProblem(2023, 1))).isEqualTo(54078)
  }
}
