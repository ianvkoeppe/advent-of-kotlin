package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Tests {
  @Test
  fun partOneExample() {
    assertThat(Day8.partOne(Filer.read(2023, 8, "example-1.txt"))).isEqualTo(2)
    assertThat(Day8.partOne(Filer.read(2023, 8, "example-2.txt"))).isEqualTo(6)
  }

  @Test
  fun partOne() {
    assertThat(Day8.partOne(Filer.readProblem(2023, 8))).isEqualTo(12643)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day8.partTwo(Filer.read(2023, 8, "example-1.txt"))).isEqualTo(2)
    assertThat(Day8.partTwo(Filer.read(2023, 8, "example-2.txt"))).isEqualTo(6)
    assertThat(Day8.partTwo(Filer.read(2023, 8, "example-3.txt"))).isEqualTo(6)
  }

  @Test
  fun partTwo() {
    assertThat(Day8.partTwo(Filer.readProblem(2023, 8))).isEqualTo(13133452426987)
  }
}
