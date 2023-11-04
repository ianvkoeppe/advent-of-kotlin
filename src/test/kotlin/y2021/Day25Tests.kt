package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day25Tests {
  @Test
  fun partOneExample() {
    assertThat(Day25.partOne(Filer.readExample(2021, 25))).isEqualTo(58)
  }

  @Test
  fun partOne() {
    assertThat(Day25.partOne(Filer.readProblem(2021, 25))).isEqualTo(360)
  }
}
