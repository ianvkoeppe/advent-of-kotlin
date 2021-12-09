package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Tests {
  @Test
  fun partOneExample() {
    assertThat(Day9.partOne(Reader.read("src/test/resources/day9/example.txt"))).isEqualTo(15)
  }

  @Test
  fun partOne() {
    assertThat(Day9.partOne(Reader.read("src/test/resources/day9/problem.txt"))).isEqualTo(550)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day9.partTwo(Reader.read("src/test/resources/day9/example.txt"))).isEqualTo(1134)
  }

  @Test
  fun partTwo() {
    assertThat(Day9.partTwo(Reader.read("src/test/resources/day9/problem.txt"))).isEqualTo(1100682)
  }
}