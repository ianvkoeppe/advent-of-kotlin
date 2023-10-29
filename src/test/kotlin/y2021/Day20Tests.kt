package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day20Tests {
  @Test
  fun partOneExample() {
    assertThat(Day20.partOne(Reader.readExample(2021, 20))).isEqualTo(35)
  }

  @Test
  fun partOne() {
    assertThat(Day20.partOne(Reader.readProblem(2021, 20))).isEqualTo(5846)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day20.partTwo(Reader.readExample(2021, 20))).isEqualTo(3351)
  }

  @Test
  fun partTwo() {
    assertThat(Day20.partTwo(Reader.readProblem(2021, 20))).isEqualTo(21149)
  }
}
