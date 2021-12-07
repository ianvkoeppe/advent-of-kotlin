package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {
  @Test
  fun partOneExample() {
    assertThat(Day6.partOne(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(5934)
  }

  @Test
  fun partOne() {
    assertThat(Day6.partOne(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(362346)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day6.partTwo(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(26984457539)
  }

  @Test
  fun partTwo() {
    assertThat(Day6.partTwo(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(1639643057051)
  }
}