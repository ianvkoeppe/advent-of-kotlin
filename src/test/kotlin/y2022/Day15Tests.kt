package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day15Tests {
  @Test
  fun partOneExample() {
    assertThat(Day15.partOne(Filer.readExample(2022, 15), 10)).isEqualTo(26)
  }

  @Test
  fun partOne() {
    assertThat(Day15.partOne(Filer.readProblem(2022, 15), 2000000)).isEqualTo(5108096)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day15.partTwo(Filer.readExample(2022, 15), 20)).isEqualTo(56000011)
  }

  @Test
  fun partTwo() {
    assertThat(Day15.partTwo(Filer.readProblem(2022, 15), 4000000)).isEqualTo(10553942650264L)
  }
}
