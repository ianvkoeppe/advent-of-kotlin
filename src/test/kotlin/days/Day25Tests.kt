package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day25Tests {
  @Test
  fun partOneExample() {
    assertThat(Day25.partOne(Reader.read("src/test/resources/day25/example.txt"))).isEqualTo(58)
  }

  @Test
  fun partOne() {
    assertThat(Day25.partOne(Reader.read("src/test/resources/day25/problem.txt"))).isEqualTo(360)
  }
}