package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Tests {
  @Test
  fun partOneExample() {
    assertThat(Day5.partOne(Reader.read("src/test/resources/day5/example.txt"))).isEqualTo(5)
  }

  @Test
  fun partOne() {
    assertThat(Day5.partOne(Reader.read("src/test/resources/day5/problem.txt"))).isEqualTo(6007)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day5.partTwo(Reader.read("src/test/resources/day5/example.txt"))).isEqualTo(12)
  }

  @Test
  fun partTwo() {
    assertThat(Day5.partTwo(Reader.read("src/test/resources/day5/problem.txt"))).isEqualTo(19349)
  }
}