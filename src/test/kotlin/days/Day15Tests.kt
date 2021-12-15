package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day15Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day15.partOne(Reader.read("src/test/resources/day15/example.txt"))).isEqualTo(40)
  }

  @Test
  fun partOne() {
    assertThat(Day15.partOne(Reader.read("src/test/resources/day15/problem.txt"))).isEqualTo(523)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day15.partTwo(Reader.read("src/test/resources/day15/example.txt"))).isEqualTo(315)
  }

  @Test
  fun partTwo() {
    assertThat(Day15.partTwo(Reader.read("src/test/resources/day15/problem.txt"))).isEqualTo(2876)
  }
}