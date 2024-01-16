package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day23Tests {
  @Test
  fun partOneExample() {
    assertThat(Day23.partOne(Filer.readExample(2023, 23))).isEqualTo(94)
  }

  @Test
  fun partOne() {
    assertThat(Day23.partOne(Filer.readProblem(2023, 23))).isEqualTo(2282)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day23.partTwo(Filer.readExample(2023, 23))).isEqualTo(154)
  }

  @Test
  @Disabled("26s")
  fun partTwo() {
    assertThat(Day23.partTwo(Filer.readProblem(2023, 23))).isEqualTo(6646)
  }
}
