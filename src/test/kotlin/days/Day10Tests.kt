package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Tests {
  @Test
  fun partOneExample() {
    assertThat(Day10.partOne(Reader.read("src/test/resources/day10/example.txt"))).isEqualTo(26397)
  }

  @Test
  fun partOne() {
    assertThat(Day10.partOne(Reader.read("src/test/resources/day10/problem.txt"))).isEqualTo(415953)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day10.partTwo(Reader.read("src/test/resources/day10/example.txt"))).isEqualTo(288957)
  }

  @Test
  fun partTwo() {
    assertThat(Day10.partTwo(Reader.read("src/test/resources/day10/problem.txt"))).isEqualTo(2292863731)
  }
}