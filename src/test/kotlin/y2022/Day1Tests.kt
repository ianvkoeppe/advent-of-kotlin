package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Reader.readExample(2022, 1))).isEqualTo(24000)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Reader.readProblem(2022, 1))).isEqualTo(72017)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Reader.readExample(2022, 1))).isEqualTo(45000)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Reader.readProblem(2022, 1))).isEqualTo(212520)
  }
}
