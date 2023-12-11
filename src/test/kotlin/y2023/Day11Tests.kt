package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Tests {
  @Test
  fun partOneExample() {
    assertThat(Day11.partOne(Filer.readExample(2023, 11))).isEqualTo(374)
  }

  @Test
  fun partOne() {
    assertThat(Day11.partOne(Filer.readProblem(2023, 11))).isEqualTo(9742154)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day11.partTwo(Filer.readExample(2023, 11), 10)).isEqualTo(1030)
    assertThat(Day11.partTwo(Filer.readExample(2023, 11), 100)).isEqualTo(8410)
  }

  @Test
  fun partTwo() {
    assertThat(Day11.partTwo(Filer.readProblem(2023, 11), 1000000)).isEqualTo(411142919886)
  }
}
