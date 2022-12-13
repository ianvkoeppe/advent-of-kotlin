package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExample() {
    assertThat(Day13.partOne(Reader.readExample(2022, 13))).isEqualTo(13)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Reader.readProblem(2022, 13))).isEqualTo(5196)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Reader.readExample( 2022, 13))).isEqualTo(140)
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Reader.readProblem(2022, 13))).isEqualTo(22134)
  }
}