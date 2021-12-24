package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day23Tests {
  @Test
  fun partOneExample() {
    assertThat(Day23.partOne(Reader.read("src/test/resources/day23/example.txt"))).isEqualTo(12521)
  }

  @Test
  fun partOne() {
    assertThat(Day23.partOne(Reader.read("src/test/resources/day23/problem.txt"))).isEqualTo(14467)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day23.partTwo(Reader.read("src/test/resources/day23/example.txt"))).isEqualTo(44169)
  }

  @Test
  fun partTwo() {
    assertThat(Day23.partTwo(Reader.read("src/test/resources/day23/problem.txt"))).isEqualTo(48759)
  }
}