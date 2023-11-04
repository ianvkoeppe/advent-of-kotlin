package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day13.partOne(Filer.readExample(2021, 13))).isEqualTo(17)
  }

  @Test
  fun partOne() {
    assertThat(Day13.partOne(Filer.readProblem(2021, 13))).isEqualTo(751)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day13.partTwo(Filer.readExample(2021, 13)))
      .isEqualTo(
        """
       #####
       #   #
       #   #
       #   #
       #####
    """
          .trimIndent()
      )
  }

  @Test
  fun partTwo() {
    assertThat(Day13.partTwo(Filer.readProblem(2021, 13)))
      .isEqualTo(
        """
      ###   ##  #  # ###  #  # #    #  # #   
      #  # #  # #  # #  # # #  #    # #  #   
      #  # #    #### #  # ##   #    ##   #   
      ###  # ## #  # ###  # #  #    # #  #   
      #    #  # #  # # #  # #  #    # #  #   
      #     ### #  # #  # #  # #### #  # ####
    """
          .trimIndent()
      )
  }
}
