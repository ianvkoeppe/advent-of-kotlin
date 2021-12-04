package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Tests {
  @Test
  fun partOneExample() {
    assertThat(Day4.partOne(Reader.read("src/test/resources/day4/example.txt"))).isEqualTo(4512)
  }

  @Test
  fun partOne() {
    assertThat(Day4.partOne(Reader.read("src/test/resources/day4/problem.txt"))).isEqualTo(60368)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day4.partTwo(Reader.read("src/test/resources/day4/example.txt"))).isEqualTo(1924)
  }

  @Test
  fun partTwo() {
    assertThat(Day4.partTwo(Reader.read("src/test/resources/day4/problem.txt"))).isEqualTo(17435)
  }
}