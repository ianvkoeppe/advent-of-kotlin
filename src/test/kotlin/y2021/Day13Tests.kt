package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day13.partOne(Reader.readExample(2021, 13))).isEqualTo(17)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Reader.readProblem(2021, 13))).isEqualTo(751)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Reader.readExample(2021, 13))).isEqualTo("""
       #####
       #   #
       #   #
       #   #
       #####
    """.trimIndent())
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Reader.readProblem(2021, 13))).isEqualTo("""
      ###   ##  #  # ###  #  # #    #  # #   
      #  # #  # #  # #  # # #  #    # #  #   
      #  # #    #### #  # ##   #    ##   #   
      ###  # ## #  # ###  # #  #    # #  #   
      #    #  # #  # # #  # #  #    # #  #   
      #     ### #  # #  # #  # #### #  # ####
    """.trimIndent())
  }
}