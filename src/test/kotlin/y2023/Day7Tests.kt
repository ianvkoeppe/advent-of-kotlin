package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Tests {
  @Test
  fun partOneExample() {
    assertThat(Day7.partOne(Filer.readExample(2023, 7))).isEqualTo(6440)
  }

  @Test
  fun partOne() {
    assertThat(Day7.partOne(Filer.readProblem(2023, 7))).isEqualTo(251287184)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day7.partTwo(Filer.readExample(2023, 7))).isEqualTo(5905)
  }

  @Test
  fun partTwo() {
    assertThat(Day7.partTwo(Filer.readProblem(2023, 7))).isEqualTo(250757288)
  }
}
