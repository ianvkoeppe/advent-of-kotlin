package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Reader.read("src/test/resources/day1/example.txt"))).isEqualTo(7)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Reader.read("src/test/resources/day1/problem.txt"))).isEqualTo(1184)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Reader.read("src/test/resources/day1/example.txt"))).isEqualTo(5)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Reader.read("src/test/resources/day1/problem.txt"))).isEqualTo(1158)
  }
}