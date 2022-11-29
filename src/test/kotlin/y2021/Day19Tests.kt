package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExample() {
    assertThat(Day19.partOne(Reader.readExample(2021, 19))).isEqualTo(79)
  }

  @Test
  @Disabled
  fun partOne() {
    assertThat(Day19.partOne(Reader.readProblem(2021, 19))).isEqualTo(383)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Reader.readExample(2021, 19))).isEqualTo(3621)
  }

  @Test
  @Disabled
  fun partTwo() {
    assertThat(Day19.partTwo(Reader.readProblem(2021, 19))).isEqualTo(9854)
  }
}