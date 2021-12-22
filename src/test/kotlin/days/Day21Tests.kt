package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day21Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day21.partOne(Reader.read("src/test/resources/day21/example.txt"))).isEqualTo(739785)
  }

  @Test
  fun partOne() {
    assertThat(Day21.partOne(Reader.read("src/test/resources/day21/problem.txt"))).isEqualTo(513936)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day21.partTwo(Reader.read("src/test/resources/day21/example.txt"))).isEqualTo(444356092776315)
  }

  @Test
  fun partTwo() {
    assertThat(Day21.partTwo(Reader.read("src/test/resources/day21/problem.txt"))).isEqualTo(105619718613031L)
  }
}