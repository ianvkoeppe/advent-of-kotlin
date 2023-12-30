package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExample() {
    assertThat(Day13.partOne(Filer.readExample(2023, 13))).isEqualTo(405)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Filer.readProblem(2023, 13))).isEqualTo(40006)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Filer.readExample(2023, 13))).isEqualTo(400)
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Filer.readProblem(2023, 13))).isEqualTo(28627)
  }
}
