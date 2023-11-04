package y2022

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day25Tests {
  @Test
  fun partOneExample() {
    assertThat(Day25.partOne(Filer.readExample(2022, 25))).isEqualTo("2=-1=0")
  }

  @Test
  fun partOne() {
    assertThat(Day25.partOne(Filer.readProblem(2022, 25))).isEqualTo("2-=0-=-2=111=220=100")
  }
}
