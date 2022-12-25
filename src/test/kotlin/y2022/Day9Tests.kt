package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Tests {
  @Test
  fun partOneExample() {
    assertThat(Day9.partOne(Reader.read("example-1.txt", 2022, 9))).isEqualTo(13)
  }

  @Test
  fun partOne() {
    assertThat(Day9.partOne(Reader.readProblem(2022, 9))).isEqualTo(5695)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day9.partTwo(Reader.read("example-1.txt", 2022, 9 ))).isEqualTo(1)
    assertThat(Day9.partTwo(Reader.read("example-2.txt", 2022, 9))).isEqualTo(36)
  }

  @Test
  fun partTwo() {
    assertThat(Day9.partTwo(Reader.readProblem(2022, 9))).isEqualTo(2434)
  }
}