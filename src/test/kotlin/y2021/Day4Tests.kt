package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Tests {
  @Test
  fun partOneExample() {
    assertThat(Day4.partOne(Reader.readExample(2021, 4))).isEqualTo(4512)
  }

  @Test
  fun partOne() {
    assertThat(Day4.partOne(Reader.readProblem(2021, 4))).isEqualTo(60368)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day4.partTwo(Reader.readExample(2021, 4))).isEqualTo(1924)
  }

  @Test
  fun partTwo() {
    assertThat(Day4.partTwo(Reader.readProblem(2021, 4))).isEqualTo(17435)
  }
}