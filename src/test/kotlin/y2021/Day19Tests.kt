package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExample() {
    assertThat(Day19.partOne(Filer.readExample(2021, 19))).isEqualTo(79)
  }

  @Test
  @Disabled("20s")
  fun partOne() {
    assertThat(Day19.partOne(Filer.readProblem(2021, 19))).isEqualTo(383)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Filer.readExample(2021, 19))).isEqualTo(3621)
  }

  @Test
  @Disabled("20s")
  fun partTwo() {
    assertThat(Day19.partTwo(Filer.readProblem(2021, 19))).isEqualTo(9854)
  }
}
