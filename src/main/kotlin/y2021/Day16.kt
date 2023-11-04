package y2021

import kotlin.math.max
import kotlin.math.min

object Day16 {
  data class Offset(val start: Int, val length: Int = 0)

  data class Packet(val version: Int, val type: Int, val value: Long?, val packets: List<Packet>, val binary: String)

  private val hexes = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
  private val hexToBinary =
    hexes.mapIndexed { i, char -> char to String.format("%4s", i.toString(2)).replace(' ', '0') }.toMap()

  private val version = Offset(0, 3)
  private val type = Offset(version.start + version.length, 3)
  private val lengthType = Offset(type.start + type.length, 1)
  private val literal = Offset(type.start + type.length)
  private val packetBitLength = Offset(lengthType.start + lengthType.length, 15)
  private val packetCount = Offset(lengthType.start + lengthType.length, 11)
  private const val literalChunks = 5

  private val functions =
    mapOf<Int, (Long, Long) -> Long>(
      0 to { a, b -> a + b },
      1 to { a, b -> a * b },
      2 to { a, b -> min(a, b) },
      3 to { a, b -> max(a, b) },
      5 to { a, b -> if (a > b) 1 else 0 },
      6 to { a, b -> if (a < b) 1 else 0 },
      7 to { a, b -> if (a == b) 1 else 0 },
    )

  fun partOne(lines: List<String>): Long {
    val binary = lines.first().map(hexToBinary::getValue).joinToString("")
    return sumVersions(parse(binary))
  }

  fun partTwo(lines: List<String>): Long {
    val binary = lines.first().map(hexToBinary::getValue).joinToString("")
    return reduce(parse(binary))
  }

  private fun parse(binary: String): Packet {
    val version = binary.drop(version.start).take(version.length).toInt(2)
    val type = binary.drop(type.start).take(type.length).toInt(2)
    val lengthType = binary.drop(lengthType.start).take(lengthType.length).toInt(2)

    return when ((type to lengthType)) {
      (4 to lengthType) -> createLiteralPacket(version, type, binary)
      (type to 0) ->
        createOperatorPacket(version, type, binary, packetBitLength) { packets ->
          packets.sumOf { packet -> packet.binary.length }
        }
      else -> createOperatorPacket(version, type, binary, packetCount) { it.size }
    }
  }

  private fun createLiteralPacket(version: Int, type: Int, binary: String): Packet {
    val chunks = binary.drop(literal.start).chunked(literalChunks)
    val literalChunks = chunks.take(chunks.takeWhile { it.first() == '1' }.count() + 1)
    val literalPayload = literalChunks.joinToString("")
    val literal = literalChunks.joinToString("") { it.drop(1) }.toLong(2)
    return Packet(version, type, literal, emptyList(), binary.take(this.literal.start + literalPayload.length))
  }

  private fun createOperatorPacket(
    version: Int,
    type: Int,
    binary: String,
    offset: Offset,
    terminatingValue: (List<Packet>) -> Int
  ): Packet {
    val subPackets = parseUntil(binary, offset, terminatingValue)
    return Packet(
      version,
      type,
      null,
      subPackets,
      binary.take(offset.start + offset.length + subPackets.sumOf { it.binary.length })
    )
  }

  private fun parseUntil(binary: String, offset: Offset, terminatingValue: (List<Packet>) -> Int): List<Packet> {
    val endValue = binary.drop(offset.start).take(offset.length).toInt(2)
    val subPacketPayload = binary.drop(offset.start + offset.length)

    val subPackets = mutableListOf<Packet>()
    while (terminatingValue(subPackets) < endValue) {
      subPackets.add(parse(subPacketPayload.drop(subPackets.sumOf { it.binary.length })))
    }
    return subPackets
  }

  private fun sumVersions(packet: Packet): Long {
    return packet.version + packet.packets.sumOf(::sumVersions)
  }

  private fun reduce(packet: Packet): Long {
    return packet.value
      ?: packet.packets
        .drop(1)
        .fold(packet.copy(value = reduce(packet.packets.first()))) { p, next ->
          p.copy(value = functions.getValue(packet.type)(p.value!!, reduce(next)))
        }
        .value!!
  }
}
