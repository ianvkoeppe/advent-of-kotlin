package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day22Tests {
  @Test
  fun partOneExample() {
    assertThat(Day22.partOne(Reader.readExample(2022, 22))).isEqualTo(6032)
  }

  @Test
  fun partOne() {
    assertThat(Day22.partOne(Reader.readProblem(2022, 22))).isEqualTo(64256)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day22.partTwo(Reader.readExample( 2022, 22))).isEqualTo(5031)
  }

  @Test
  fun partTwo() {
    assertThat(Day22.partTwo(Reader.readProblem(2022, 22))).isEqualTo(109224)
  }
}