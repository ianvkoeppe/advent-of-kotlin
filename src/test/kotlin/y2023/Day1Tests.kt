package y2023

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Reader.readExample(2023, 1))).isEqualTo(0)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Reader.readProblem(2023, 1))).isEqualTo(0)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Reader.readExample(2023, 1))).isEqualTo(0)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Reader.readProblem(2023, 1))).isEqualTo(0)
  }
}
