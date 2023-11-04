package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Tests {
  @Test
  fun partOneExample() {
    assertThat(Day2.partOne(Filer.readExample(2021, 2))).isEqualTo(150)
  }

  @Test
  fun partOne() {
    assertThat(Day2.partOne(Filer.readProblem(2021, 2))).isEqualTo(1882980)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day2.partTwo(Filer.readExample(2021, 2))).isEqualTo(900)
  }

  @Test
  fun partTwo() {
    assertThat(Day2.partTwo(Filer.readProblem(2021, 2))).isEqualTo(1971232560)
  }
}
