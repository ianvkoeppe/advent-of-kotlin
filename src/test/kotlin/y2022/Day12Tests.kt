package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Tests {
  @Test
  fun partOneExample() {
    assertThat(Day12.partOne(Reader.readExample(2022, 12))).isEqualTo(31)
  }

  @Test
  fun partOne() {
    assertThat(Day12.partOne(Reader.readProblem(2022, 12))).isEqualTo(468)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day12.partTwo(Reader.readExample(2022, 12))).isEqualTo(29)
  }

  @Test
  fun partTwo() {
    assertThat(Day12.partTwo(Reader.readProblem(2022, 12))).isEqualTo(459)
  }
}
