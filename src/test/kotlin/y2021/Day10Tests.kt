package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Tests {
  @Test
  fun partOneExample() {
    assertThat(Day10.partOne(Reader.readExample(2021, 10))).isEqualTo(26397)
  }

  @Test
  fun partOne() {
    assertThat(Day10.partOne(Reader.readProblem(2021, 10))).isEqualTo(415953)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day10.partTwo(Reader.readExample(2021, 10))).isEqualTo(288957)
  }

  @Test
  fun partTwo() {
    assertThat(Day10.partTwo(Reader.readProblem(2021, 10))).isEqualTo(2292863731)
  }
}