package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Tests {
  @Test
  fun partOneExample() {
    assertThat(Day3.partOne(Filer.readExample(2023, 3))).isEqualTo(4361)
  }

  @Test
  fun partOne() {
    assertThat(Day3.partOne(Filer.readProblem(2023, 3))).isEqualTo(530849)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day3.partTwo(Filer.readExample(2023, 3))).isEqualTo(467835)
  }

  @Test
  fun partTwo() {
    assertThat(Day3.partTwo(Filer.readProblem(2023, 3))).isEqualTo(84900879)
  }
}
