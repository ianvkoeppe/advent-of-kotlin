package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Tests {
  @Test
  fun partOneExample() {
    assertThat(Day3.partOne(Filer.readExample(2021, 3))).isEqualTo(198)
  }

  @Test
  fun partOne() {
    assertThat(Day3.partOne(Filer.readProblem(2021, 3))).isEqualTo(775304)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day3.partTwo(Filer.readExample(2021, 3))).isEqualTo(230)
  }

  @Test
  fun partTwo() {
    assertThat(Day3.partTwo(Filer.readProblem(2021, 3))).isEqualTo(1370737)
  }
}
