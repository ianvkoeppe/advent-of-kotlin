package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day21Tests {
  @Test
  fun partOneExample() {
    assertThat(Day21.partOne(Filer.readExample(2023, 21), 6)).isEqualTo(16)
  }

  @Test
  fun partOne() {
    assertThat(Day21.partOne(Filer.readProblem(2023, 21), 64)).isEqualTo(3699)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 6)).isEqualTo(16)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 10)).isEqualTo(50)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 50)).isEqualTo(1594)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 71)).isEqualTo(3282)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 72)).isEqualTo(3380)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 100)).isEqualTo(6536)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 500)).isEqualTo(167004)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 1000)).isEqualTo(668697)
    assertThat(Day21.partTwo(Filer.readExample(2023, 21), 5000)).isEqualTo(16733044)
  }

  @Test
  fun partTwo() {
    assertThat(Day21.partTwo(Filer.readProblem(2023, 21), 26501365)).isEqualTo(613391294577878)
  }
}
