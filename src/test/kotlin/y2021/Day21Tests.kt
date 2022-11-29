package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day21Tests {
  @Test
  fun partOneExample() {
    assertThat(Day21.partOne(Reader.readExample(2021, 21))).isEqualTo(739785)
  }

  @Test
  fun partOne() {
    assertThat(Day21.partOne(Reader.readProblem(2021, 21))).isEqualTo(513936)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day21.partTwo(Reader.readExample(2021, 21))).isEqualTo(444356092776315)
  }

  @Test
  fun partTwo() {
    assertThat(Day21.partTwo(Reader.readProblem(2021, 21))).isEqualTo(105619718613031L)
  }
}