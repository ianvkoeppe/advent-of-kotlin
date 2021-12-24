package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day17Tests {
  @Test
  fun partOneExample() {
    assertThat(Day17.partOne(Reader.read("src/test/resources/day17/example.txt"))).isEqualTo(45)
  }

  @Test
  fun partOne() {
    assertThat(Day17.partOne(Reader.read("src/test/resources/day17/problem.txt"))).isEqualTo(6903)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day17.partTwo(Reader.read("src/test/resources/day17/example.txt"))).isEqualTo(112)
  }

  @Test
  fun partTwo() {
    assertThat(Day17.partTwo(Reader.read("src/test/resources/day17/problem.txt"))).isEqualTo(2351)
  }
}