package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Tests {
  @Test
  fun partOneExample() {
    assertThat(Day10.partOne(Reader.readExample(2022, 10))).isEqualTo(13140)
  }

  @Test
  fun partOne() {
    assertThat(Day10.partOne(Reader.readProblem(2022, 10))).isEqualTo(15020L)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day10.partTwo(Reader.readExample( 2022, 10 ))).isEqualTo("""
      ##  ##  ##  ##  ##  ##  ##  ##  ##  ##  
      ###   ###   ###   ###   ###   ###   ### 
      ####    ####    ####    ####    ####    
      #####     #####     #####     #####     
      ######      ######      ######      ####
      #######       #######       #######     
    """.trimIndent())
  }

  @Test
  fun partTwo() {
    assertThat(Day10.partTwo(Reader.readProblem(2022, 10))).isEqualTo("""
      #### #### #  #  ##  #    ###   ##  ###  
      #    #    #  # #  # #    #  # #  # #  # 
      ###  ###  #  # #    #    #  # #  # #  # 
      #    #    #  # # ## #    ###  #### ###  
      #    #    #  # #  # #    #    #  # #    
      #### #     ##   ### #### #    #  # #    
    """.trimIndent())
  }
}