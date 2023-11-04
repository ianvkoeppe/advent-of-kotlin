package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day24Tests {
  @Test
  fun partOne() {
    assertThat(Day24.partOne(Filer.readProblem(2021, 24))).isEqualTo(95299897999897)
  }

  @Test
  fun partTwo() {
    assertThat(Day24.partTwo(Filer.readProblem(2021, 24))).isEqualTo(31111121382151)
  }
}
