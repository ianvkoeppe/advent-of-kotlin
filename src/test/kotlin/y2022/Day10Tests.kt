package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Tests {
  @Test
  fun partOneExample() {
    assertThat(Day10.partOne(Filer.readExample(2022, 10))).isEqualTo(13140)
  }

  @Test
  fun partOne() {
    assertThat(Day10.partOne(Filer.readProblem(2022, 10))).isEqualTo(15020L)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day10.partTwo(Filer.readExample(2022, 10)))
      .isEqualTo(
        """
      ##  ##  ##  ##  ##  ##  ##  ##  ##  ##  
      ###   ###   ###   ###   ###   ###   ### 
      ####    ####    ####    ####    ####    
      #####     #####     #####     #####     
      ######      ######      ######      ####
      #######       #######       #######     
    """
          .trimIndent()
      )
  }

  @Test
  fun partTwo() {
    assertThat(Day10.partTwo(Filer.readProblem(2022, 10)))
      .isEqualTo(
        """
      #### #### #  #  ##  #    ###   ##  ###  
      #    #    #  # #  # #    #  # #  # #  # 
      ###  ###  #  # #    #    #  # #  # #  # 
      #    #    #  # # ## #    ###  #### ###  
      #    #    #  # #  # #    #    #  # #    
      #### #     ##   ### #### #    #  # #    
    """
          .trimIndent()
      )
  }
}
