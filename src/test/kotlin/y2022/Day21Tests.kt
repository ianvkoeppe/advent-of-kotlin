package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day21Tests {
  @Test
  fun partOneExample() {
    assertThat(Day21.partOne(Filer.readExample(2022, 21))).isEqualTo(152)
  }

  @Test
  fun partOne() {
    assertThat(Day21.partOne(Filer.readProblem(2022, 21))).isEqualTo(38914458159166)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day21.partTwo(Filer.readExample(2022, 21))).isEqualTo(301)
  }

  @Test
  fun partTwo() {
    assertThat(Day21.partTwo(Filer.readProblem(2022, 21))).isEqualTo(3665520865940)
  }
}
