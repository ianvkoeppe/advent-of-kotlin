package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {
  @Test
  fun partOneExample() {
    assertThat(Day6.DynamicProgramming.partOne(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(5934)
    assertThat(Day6.ReduceThenSimulate.partOne(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(5934)
  }

  @Test
  fun partOne() {
    assertThat(Day6.DynamicProgramming.partOne(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(362346)
    assertThat(Day6.ReduceThenSimulate.partOne(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(362346)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day6.DynamicProgramming.partTwo(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(26984457539)
    assertThat(Day6.ReduceThenSimulate.partTwo(Reader.read("src/test/resources/day6/example.txt"))).isEqualTo(26984457539)
  }

  @Test
  fun partTwo() {
    assertThat(Day6.DynamicProgramming.partTwo(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(1639643057051)
    assertThat(Day6.ReduceThenSimulate.partTwo(Reader.read("src/test/resources/day6/problem.txt"))).isEqualTo(1639643057051)
  }
}