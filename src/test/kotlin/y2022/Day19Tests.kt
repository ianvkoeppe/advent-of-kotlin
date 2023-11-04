package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExample() {
    assertThat(Day19.partOne(Filer.readExample(2022, 19))).isEqualTo(33)
  }

  @Test
  fun partOne() {
    assertThat(Day19.partOne(Filer.readProblem(2022, 19))).isEqualTo(2301)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Filer.readExample(2022, 19))).isEqualTo(3472)
  }

  @Test
  fun partTwo() {
    assertThat(Day19.partTwo(Filer.readProblem(2022, 19))).isEqualTo(10336)
  }
}
