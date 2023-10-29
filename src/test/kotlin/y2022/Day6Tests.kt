package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {
  @Test
  fun partOneExample() {
    assertThat(Day6.partOne(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))).isEqualTo(7)
    assertThat(Day6.partOne(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz"))).isEqualTo(5)
    assertThat(Day6.partOne(listOf("nppdvjthqldpwncqszvftbrmjlhg"))).isEqualTo(6)
    assertThat(Day6.partOne(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))).isEqualTo(10)
    assertThat(Day6.partOne(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))).isEqualTo(11)
  }

  @Test
  fun partOne() {
    assertThat(Day6.partOne(Reader.readProblem(2022, 6))).isEqualTo(1702)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day6.partTwo(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))).isEqualTo(19)
    assertThat(Day6.partTwo(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz"))).isEqualTo(23)
    assertThat(Day6.partTwo(listOf("nppdvjthqldpwncqszvftbrmjlhg"))).isEqualTo(23)
    assertThat(Day6.partTwo(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))).isEqualTo(29)
    assertThat(Day6.partTwo(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))).isEqualTo(26)
  }

  @Test
  fun partTwo() {
    assertThat(Day6.partTwo(Reader.readProblem(2022, 6))).isEqualTo(3559)
  }
}
