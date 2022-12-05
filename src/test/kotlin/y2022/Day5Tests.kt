package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Tests {
  @Test
  fun partOneExample() {
    assertThat(Day5.partOne(Reader.readExample(2022, 5))).isEqualTo("CMZ")
  }

  @Test
  fun partOne() {
    assertThat(Day5.partOne(Reader.readProblem(2022, 5))).isEqualTo("SHMSDGZVC")
  }

  @Test
  fun partTwoExample() {
    assertThat(Day5.partTwo(Reader.readExample(2022, 5))).isEqualTo("MCD")
  }

  @Test
  fun partTwo() {
    assertThat(Day5.partTwo(Reader.readProblem(2022, 5))).isEqualTo("VRZGHDFBQ")
  }
}