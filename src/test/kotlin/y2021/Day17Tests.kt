package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day17Tests {
  @Test
  fun partOneExample() {
    assertThat(Day17.partOne(Reader.readExample(2021, 17))).isEqualTo(45)
  }

  @Test
  fun partOne() {
    assertThat(Day17.partOne(Reader.readProblem(2021, 17))).isEqualTo(6903)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day17.partTwo(Reader.readExample(2021, 17))).isEqualTo(112)
  }

  @Test
  fun partTwo() {
    assertThat(Day17.partTwo(Reader.readProblem(2021, 17))).isEqualTo(2351)
  }
}
