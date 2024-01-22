package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day25Tests {
  @Test
  fun partOneExample() {
    assertThat(Day25.partOne(Filer.readExample(2023, 25))).isEqualTo(54)
  }

  @Test
  fun partOne() {
    assertThat(Day25.partOne(Filer.readProblem(2023, 25))).isEqualTo(592171)
  }
}
