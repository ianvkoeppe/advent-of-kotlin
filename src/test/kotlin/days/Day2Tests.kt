package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Tests {
  @Test
  fun partOneExample() {
    assertThat(Day2.partOne(Reader.read("src/test/resources/day2/example.txt"))).isEqualTo(150)
  }

  @Test
  fun partOne() {
    assertThat(Day2.partOne(Reader.read("src/test/resources/day2/problem.txt"))).isEqualTo(1882980)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day2.partTwo(Reader.read("src/test/resources/day2/example.txt"))).isEqualTo(900)
  }

  @Test
  fun partTwo() {
    assertThat(Day2.partTwo(Reader.read("src/test/resources/day2/problem.txt"))).isEqualTo(1971232560)
  }
}