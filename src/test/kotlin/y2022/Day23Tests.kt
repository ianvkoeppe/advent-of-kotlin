package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day23Tests {
  @Test
  fun partOneExample() {
    assertThat(Day23.partOne(Filer.readExample(2022, 23))).isEqualTo(110)
  }

  @Test
  fun partOne() {
    assertThat(Day23.partOne(Filer.readProblem(2022, 23))).isEqualTo(4034)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day23.partTwo(Filer.readExample(2022, 23))).isEqualTo(20)
  }

  @Test
  fun partTwo() {
    assertThat(Day23.partTwo(Filer.readProblem(2022, 23))).isEqualTo(960)
  }
}
