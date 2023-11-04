package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExample() {
    assertThat(Day13.partOne(Filer.readExample(2022, 13))).isEqualTo(13)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Filer.readProblem(2022, 13))).isEqualTo(5196)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Filer.readExample(2022, 13))).isEqualTo(140)
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Filer.readProblem(2022, 13))).isEqualTo(22134)
  }
}
