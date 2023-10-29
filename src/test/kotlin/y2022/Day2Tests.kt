package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Tests {
  @Test
  fun partOneExample() {
    assertThat(Day2.partOne(Reader.readExample(2022, 2))).isEqualTo(15)
  }

  @Test
  fun partOne() {
    assertThat(Day2.partOne(Reader.readProblem(2022, 2))).isEqualTo(10404)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day2.partTwo(Reader.readExample(2022, 2))).isEqualTo(12)
  }

  @Test
  fun partTwo() {
    assertThat(Day2.partTwo(Reader.readProblem(2022, 2))).isEqualTo(10334)
  }
}
