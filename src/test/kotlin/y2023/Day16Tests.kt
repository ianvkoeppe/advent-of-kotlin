package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day16Tests {
  @Test
  fun partOneExample() {
    assertThat(Day16.partOne(Filer.readExample(2023, 16))).isEqualTo(46)
  }

  @Test
  fun partOne() {
    assertThat(Day16.partOne(Filer.readProblem(2023, 16))).isEqualTo(6855)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day16.partTwo(Filer.readExample(2023, 16))).isEqualTo(51)
  }

  @Test
  fun partTwo() {
    assertThat(Day16.partTwo(Filer.readProblem(2023, 16))).isEqualTo(7513)
  }
}
