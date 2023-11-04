package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day22Tests {
  @Test
  fun partOneExample() {
    assertThat(Day22.partOne(Filer.readExample(2021, 22))).isEqualTo(590784)
  }

  @Test
  fun partOne() {
    assertThat(Day22.partOne(Filer.readProblem(2021, 22))).isEqualTo(591365)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day22.partTwo(Filer.readExample(2021, 22))).isEqualTo(39769202357779)
  }

  @Test
  fun partTwo() {
    assertThat(Day22.partTwo(Filer.readProblem(2021, 22))).isEqualTo(1211172281877240)
  }
}
