package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day22Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day22.partOne(Reader.read("src/test/resources/day22/example.txt"))).isEqualTo(590784)
  }

  @Test
  fun partOne() {
    assertThat(Day22.partOne(Reader.read("src/test/resources/day22/problem.txt"))).isEqualTo(591365)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day22.partTwo(Reader.read("src/test/resources/day22/example.txt"))).isEqualTo(39769202357779)
  }

  @Test
  fun partTwo() {
    assertThat(Day22.partTwo(Reader.read("src/test/resources/day22/problem.txt"))).isEqualTo(1211172281877240)
  }
}