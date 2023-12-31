package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day15Tests {
  @Test
  fun partOneExample() {
    assertThat(Day15.partOne(Filer.read(2023, 15, "example-1.txt"))).isEqualTo(52)
    assertThat(Day15.partOne(Filer.read(2023, 15, "example-2.txt"))).isEqualTo(1320)
  }

  @Test
  fun partOne() {
    assertThat(Day15.partOne(Filer.readProblem(2023, 15))).isEqualTo(506269)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day15.partTwo(Filer.read(2023, 15, "example-2.txt"))).isEqualTo(145)
  }

  @Test
  fun partTwo() {
    assertThat(Day15.partTwo(Filer.readProblem(2023, 15))).isEqualTo(264021)
  }
}
