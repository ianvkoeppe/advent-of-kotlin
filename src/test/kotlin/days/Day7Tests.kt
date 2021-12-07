package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Tests {
  @Test
  fun partOneExample() {
    assertThat(Day7.partOne(Reader.read("src/test/resources/day7/example.txt"))).isEqualTo(37)
  }

  @Test
  fun partOne() {
    assertThat(Day7.partOne(Reader.read("src/test/resources/day7/problem.txt"))).isEqualTo(340987)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day7.partTwo(Reader.read("src/test/resources/day7/example.txt"))).isEqualTo(168)
  }

  @Test
  fun partTwo() {
    assertThat(Day7.partTwo(Reader.read("src/test/resources/day7/problem.txt"))).isEqualTo(96987874)
  }
}