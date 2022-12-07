package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Tests {
  @Test
  fun partOneExample() {
    assertThat(Day7.partOne(Reader.readExample(2022, 7))).isEqualTo(95437)
  }

  @Test
  fun partOne() {
    assertThat(Day7.partOne(Reader.readProblem(2022, 7))).isEqualTo(1428881)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day7.partTwo(Reader.readExample(2022, 7))).isEqualTo(24933642)
  }

  @Test
  fun partTwo() {
    assertThat(Day7.partTwo(Reader.readProblem(2022, 7))).isEqualTo(10475598)
  }
}