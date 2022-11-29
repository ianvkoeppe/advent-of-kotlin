package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day16Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day16.partOne(listOf("8A004A801A8002F478"))).isEqualTo(16)
    assertThat(Day16.partOne(listOf("620080001611562C8802118E34"))).isEqualTo(12)
    assertThat(Day16.partOne(listOf("C0015000016115A2E0802F182340"))).isEqualTo(23)
    assertThat(Day16.partOne(listOf("A0016C880162017C3686B18A3D4780"))).isEqualTo(31)
  }

  @Test
  fun partOne() {
    assertThat(Day16.partOne(Reader.readProblem(2021, 16))).isEqualTo(999)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day16.partTwo(listOf("C200B40A82"))).isEqualTo(3)
    assertThat(Day16.partTwo(listOf("04005AC33890"))).isEqualTo(54)
    assertThat(Day16.partTwo(listOf("880086C3E88112"))).isEqualTo(7)
    assertThat(Day16.partTwo(listOf("CE00C43D881120"))).isEqualTo(9)
    assertThat(Day16.partTwo(listOf("D8005AC2A8F0"))).isEqualTo(1)
    assertThat(Day16.partTwo(listOf("F600BC2D8F"))).isEqualTo(0)
    assertThat(Day16.partTwo(listOf("9C005AC2F8F0"))).isEqualTo(0)
    assertThat(Day16.partTwo(listOf("9C0141080250320F1802104A08"))).isEqualTo(1)
  }

  @Test
  fun partTwo() {
    assertThat(Day16.partTwo(Reader.readProblem(2021, 16))).isEqualTo(3408662834145)
  }
}