package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day24Tests {
  @Test
  fun partOneExample() {
    assertThat(Day24.partOne(Filer.readExample(2023, 24), 7, 27)).isEqualTo(2)
  }

  @Test
  fun partOne() {
    assertThat(Day24.partOne(Filer.readProblem(2023, 24), 200000000000000, 400000000000000)).isEqualTo(25261)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day24.partTwo(Filer.readExample(2023, 24))).isEqualTo(47)
  }

  @Test
  fun partTwo() {
    assertThat(Day24.partTwo(Filer.readProblem(2023, 24))).isEqualTo(549873212220117)
  }
}
