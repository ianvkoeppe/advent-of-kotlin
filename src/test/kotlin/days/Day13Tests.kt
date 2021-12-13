package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day13.partOne(Reader.read("src/test/resources/day13/example.txt"))).isEqualTo(17)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Reader.read("src/test/resources/day13/problem.txt"))).isEqualTo(751)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Reader.read("src/test/resources/day13/example.txt"))).isEqualTo("""
       #####
       #   #
       #   #
       #   #
       #####
    """.trimIndent())
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Reader.read("src/test/resources/day13/problem.txt"))).isEqualTo("""
      ###   ##  #  # ###  #  # #    #  # #   
      #  # #  # #  # #  # # #  #    # #  #   
      #  # #    #### #  # ##   #    ##   #   
      ###  # ## #  # ###  # #  #    # #  #   
      #    #  # #  # # #  # #  #    # #  #   
      #     ### #  # #  # #  # #### #  # ####
    """.trimIndent())
  }
}