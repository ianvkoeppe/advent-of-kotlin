package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day20Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day20.partOne(Reader.read("src/test/resources/day20/example.txt"))).isEqualTo(35)
  }

  @Test
  fun partOne() {
    assertThat(Day20.partOne(Reader.read("src/test/resources/day20/problem.txt"))).isEqualTo(5846)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day20.partTwo(Reader.read("src/test/resources/day20/example.txt"))).isEqualTo(3351)
  }

  @Test
  fun partTwo() {
    assertThat(Day20.partTwo(Reader.read("src/test/resources/day20/problem.txt"))).isEqualTo(21149)
  }
}