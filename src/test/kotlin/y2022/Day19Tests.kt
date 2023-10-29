package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExample() {
    assertThat(Day19.partOne(Reader.readExample(2022, 19))).isEqualTo(33)
  }

  @Test
  fun partOne() {
    assertThat(Day19.partOne(Reader.readProblem(2022, 19))).isEqualTo(2301)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Reader.readExample(2022, 19))).isEqualTo(3472)
  }

  @Test
  fun partTwo() {
    assertThat(Day19.partTwo(Reader.readProblem(2022, 19))).isEqualTo(10336)
  }
}
