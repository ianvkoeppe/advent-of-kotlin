package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day15Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day15.partOne(Reader.readExample(2021, 15))).isEqualTo(40)
  }

  @Test
  fun partOne() {
    assertThat(Day15.partOne(Reader.readProblem(2021, 15))).isEqualTo(523)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day15.partTwo(Reader.readExample(2021, 15))).isEqualTo(315)
  }

  @Test
  fun partTwo() {
    assertThat(Day15.partTwo(Reader.readProblem(2021, 15))).isEqualTo(2876)
  }
}