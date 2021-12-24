package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day24Tests {
  @Test
  fun partOne() {
    assertThat(Day24.partOne(Reader.read("src/test/resources/day24/problem.txt"))).isEqualTo(95299897999897)
  }

  @Test
  fun partTwo() {
    assertThat(Day24.partTwo(Reader.read("src/test/resources/day24/problem.txt"))).isEqualTo(31111121382151)
  }
}