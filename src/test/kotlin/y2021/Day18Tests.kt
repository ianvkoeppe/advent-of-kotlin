package y2021

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Tests {
  @Test
  fun partOneExample() {
    assertThat(Day18.partOne(Filer.readExample(2021, 18))).isEqualTo(4140)
  }

  @Test
  fun partOne() {
    assertThat(Day18.partOne(Filer.readProblem(2021, 18))).isEqualTo(4088)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day18.partTwo(Filer.readExample(2021, 18))).isEqualTo(3993)
  }

  @Test
  fun partTwo() {
    assertThat(Day18.partTwo(Filer.readProblem(2021, 18))).isEqualTo(4536)
  }

  @Test
  fun flattenAndNest() {
    assertThat(Day18.nest(Day18.flatten(Day18.parse("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]"))).toString())
      .isEqualTo("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]")
    assertThat(Day18.nest(Day18.flatten(Day18.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"))).toString())
      .isEqualTo("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
  }

  @Test
  fun explodeAndSplit() {
    assertThat(Day18.nest(Day18.explodeAndSplit(Day18.flatten(Day18.parse("[[[[[9,8],1],2],3],4]")))).toString())
      .isEqualTo("[[[[0,9],2],3],4]")
    assertThat(Day18.nest(Day18.explodeAndSplit(Day18.flatten(Day18.parse("[7,[6,[5,[4,[3,2]]]]]")))).toString())
      .isEqualTo("[7,[6,[5,[7,0]]]]")
    assertThat(Day18.nest(Day18.explodeAndSplit(Day18.flatten(Day18.parse("[[6,[5,[4,[3,2]]]],1]")))).toString())
      .isEqualTo("[[6,[5,[7,0]]],3]")
    assertThat(
        Day18.nest(Day18.explodeAndSplit(Day18.flatten(Day18.parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"))))
          .toString()
      )
      .isEqualTo("[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
    assertThat(
        Day18.nest(Day18.explodeAndSplit(Day18.flatten(Day18.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"))))
          .toString()
      )
      .isEqualTo("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
  }

  @Test
  fun magnitude() {
    assertThat(Day18.magnitude((Day18.parse("[9,1]")))).isEqualTo(29)
    assertThat(Day18.magnitude((Day18.parse("[1,9]")))).isEqualTo(21)
    assertThat(Day18.magnitude((Day18.parse("[[9,1],[1,9]]")))).isEqualTo(129)
    assertThat(Day18.magnitude((Day18.parse("[[1,2],[[3,4],5]]")))).isEqualTo(143)
    assertThat(Day18.magnitude((Day18.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")))).isEqualTo(1384)
    assertThat(Day18.magnitude((Day18.parse("[[[[1,1],[2,2]],[3,3]],[4,4]]")))).isEqualTo(445)
    assertThat(Day18.magnitude((Day18.parse("[[[[3,0],[5,3]],[4,4]],[5,5]]")))).isEqualTo(791)
    assertThat(Day18.magnitude((Day18.parse("[[[[5,0],[7,4]],[5,5]],[6,6]]")))).isEqualTo(1137)
    assertThat(Day18.magnitude((Day18.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")))).isEqualTo(3488)
  }
}
