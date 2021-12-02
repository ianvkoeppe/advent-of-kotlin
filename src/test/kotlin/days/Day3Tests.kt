package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Tests {
  @Test
  fun partOneExample() {
    assertThat(Day3.partOne(Reader.read("src/test/resources/day3/example.txt"))).isEqualTo(0)
  }

  @Test
  fun partOne() {
    assertThat(Day3.partOne(Reader.read("src/test/resources/day3/problem.txt"))).isEqualTo(0)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day3.partTwo(Reader.read("src/test/resources/day3/example.txt"))).isEqualTo(0)
  }

  @Test
  fun partTwo() {
    assertThat(Day3.partTwo(Reader.read("src/test/resources/day3/problem.txt"))).isEqualTo(0)
  }
}