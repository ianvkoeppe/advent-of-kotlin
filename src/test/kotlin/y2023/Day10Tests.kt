package y2023

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Tests {
  @Test
  fun partOneExample() {
    assertThat(Day10.partOne(Filer.read(2023, 10, "example-1.txt"))).isEqualTo(4)
    assertThat(Day10.partOne(Filer.read(2023, 10, "example-2.txt"))).isEqualTo(8)
  }

  @Test
  fun partOne() {
    assertThat(Day10.partOne(Filer.readProblem(2023, 10))).isEqualTo(6823)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day10.partTwo(Filer.read(2023, 10, "example-3.txt"))).isEqualTo(4)
    assertThat(Day10.partTwo(Filer.read(2023, 10, "example-4.txt"))).isEqualTo(4)
    assertThat(Day10.partTwo(Filer.read(2023, 10, "example-5.txt"))).isEqualTo(8)
    assertThat(Day10.partTwo(Filer.read(2023, 10, "example-6.txt"))).isEqualTo(10)
  }

  @Test
  fun partTwo() {
    assertThat(Day10.partTwo(Filer.readProblem(2023, 10))).isLessThan(1021).isEqualTo(415)
  }
}
