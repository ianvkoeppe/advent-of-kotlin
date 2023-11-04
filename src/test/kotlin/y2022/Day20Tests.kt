package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day20Tests {
  @Test
  fun partOneExample() {
    assertThat(Day20.partOne(Filer.readExample(2022, 20))).isEqualTo(3)
  }

  @Test
  fun partOne() {
    assertThat(Day20.partOne(Filer.readProblem(2022, 20))).isEqualTo(4267)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day20.partTwo(Filer.readExample(2022, 20))).isEqualTo(1623178306)
  }

  @Test
  fun partTwo() {
    assertThat(Day20.partTwo(Filer.readProblem(2022, 20))).isEqualTo(6871725358451)
  }
}
