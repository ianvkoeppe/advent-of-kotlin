package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day20Tests {
  @Test
  fun partOneExample() {
    assertThat(Day20.partOne(Reader.readExample(2022, 20))).isEqualTo(3)
  }

  @Test
  fun partOne() {
    assertThat(Day20.partOne(Reader.readProblem(2022, 20))).isEqualTo(4267)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day20.partTwo(Reader.readExample( 2022, 20))).isEqualTo(1623178306)
  }

  @Test
  fun partTwo() {
    assertThat(Day20.partTwo(Reader.readProblem(2022, 20))).isEqualTo(6871725358451)
  }
}