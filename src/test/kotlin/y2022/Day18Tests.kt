package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Tests {
  @Test
  fun partOneExample() {
    assertThat(Day18.partOne(Reader.readExample(2022, 18))).isEqualTo(64)
  }

  @Test
  fun partOne() {
    assertThat(Day18.partOne(Reader.readProblem(2022, 18))).isEqualTo(3496)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day18.partTwo(Reader.readExample( 2022, 18))).isEqualTo(58)
  }

  @Test
  fun partTwo() {
    assertThat(Day18.partTwo(Reader.readProblem(2022, 18))).isEqualTo(2064)
  }
}