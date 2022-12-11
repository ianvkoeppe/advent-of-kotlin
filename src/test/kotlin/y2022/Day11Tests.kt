package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Tests {
  @Test
  fun partOneExample() {
    assertThat(Day11.partOne(Reader.readExample(2022, 11))).isEqualTo(10605)
  }

  @Test
  fun partOne() {
    assertThat(Day11.partOne(Reader.readProblem(2022, 11))).isEqualTo(55930L)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day11.partTwo(Reader.readExample( 2022, 11))).isEqualTo(2713310158)
  }

  @Test
  fun partTwo() {
    assertThat(Day11.partTwo(Reader.readProblem(2022, 11))).isEqualTo(14636993466)
  }
}