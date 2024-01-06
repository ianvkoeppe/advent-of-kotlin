package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day20Tests {
  @Test
  fun partOneExample() {
    assertThat(Day20.partOne(Filer.read(2023, 20, "example-1.txt"))).isEqualTo(32000000)
    assertThat(Day20.partOne(Filer.read(2023, 20, "example-2.txt"))).isEqualTo(11687500)
  }

  @Test
  fun partOne() {
    assertThat(Day20.partOne(Filer.readProblem(2023, 20))).isEqualTo(825896364)
  }

  @Test
  fun partTwo() {
    assertThat(Day20.partTwo(Filer.readProblem(2023, 20))).isEqualTo(243566897206981)
  }
}
