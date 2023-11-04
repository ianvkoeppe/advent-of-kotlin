package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day17Tests {
  @Test
  fun partOneExample() {
    assertThat(Day17.partOne(Filer.readExample(2022, 17))).isEqualTo(3068)
  }

  @Test
  fun partOne() {
    assertThat(Day17.partOne(Filer.readProblem(2022, 17))).isEqualTo(3193)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day17.partTwo(Filer.readExample(2022, 17))).isEqualTo(1514285714288)
  }

  @Test
  fun partTwo() {
    assertThat(Day17.partTwo(Filer.readProblem(2022, 17))).isEqualTo(1577650429835)
  }
}
