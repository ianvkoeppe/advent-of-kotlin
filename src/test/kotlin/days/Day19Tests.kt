package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day19.partOne(Reader.read("src/test/resources/day19/example.txt"))).isEqualTo(79)
  }

  @Test
  @Disabled
  fun partOne() {
    assertThat(Day19.partOne(Reader.read("src/test/resources/day19/problem.txt"))).isEqualTo(383)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Reader.read("src/test/resources/day19/example.txt"))).isEqualTo(3621)
  }

  @Test
  @Disabled
  fun partTwo() {
    assertThat(Day19.partTwo(Reader.read("src/test/resources/day19/problem.txt"))).isEqualTo(9854)
  }
}