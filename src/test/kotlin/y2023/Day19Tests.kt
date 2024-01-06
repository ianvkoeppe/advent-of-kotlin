package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day19Tests {
  @Test
  fun partOneExample() {
    assertThat(Day19.partOne(Filer.readExample(2023, 19))).isEqualTo(19114)
  }

  @Test
  fun partOne() {
    assertThat(Day19.partOne(Filer.readProblem(2023, 19))).isEqualTo(389114)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day19.partTwo(Filer.readExample(2023, 19))).isEqualTo(167409079868000)
  }

  @Test
  fun partTwo() {
    assertThat(Day19.partTwo(Filer.readProblem(2023, 19))).isEqualTo(125051049836302)
  }
}
