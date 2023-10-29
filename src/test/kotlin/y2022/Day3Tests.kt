package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Tests {
  @Test
  fun partOneExample() {
    assertThat(Day3.partOne(Reader.readExample(2022, 3))).isEqualTo(157)
  }

  @Test
  fun partOne() {
    assertThat(Day3.partOne(Reader.readProblem(2022, 3))).isEqualTo(8202)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day3.partTwo(Reader.readExample(2022, 3))).isEqualTo(70)
  }

  @Test
  fun partTwo() {
    assertThat(Day3.partTwo(Reader.readProblem(2022, 3))).isEqualTo(2864)
  }
}
