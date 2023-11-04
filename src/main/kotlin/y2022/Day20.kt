package y2022

import kotlin.math.abs

object Day20 {
  data class Node<T>(val v: T, var prev: Node<T>? = null, var next: Node<T>? = null) {
    fun move(times: Int, node: Node<T> = this) =
      if (times >= 0) moveTowardsTail(node, times) else moveTowardsHead(node, -times)

    private tailrec fun moveTowardsTail(node: Node<T>, times: Int) {
      if (times == 0) return

      remove(node)
      insertBetween(node, node.next, node.next?.next)
      moveTowardsTail(node, times - 1)
    }

    private tailrec fun moveTowardsHead(node: Node<T>, times: Int) {
      if (times == 0) return

      remove(node)
      insertBetween(node, node.prev?.prev, node.prev)
      moveTowardsHead(node, times - 1)
    }

    private fun remove(node: Node<T>) {
      node.prev?.next = node.next
      node.next?.prev = node.prev
    }

    private fun insertBetween(node: Node<T>, prev: Node<T>?, next: Node<T>?) {
      prev?.next = node
      node.prev = prev
      node.next = next
      next?.prev = node
    }
  }

  data class EncryptedNumbers(private val head: Node<Long>, val original: List<Node<Long>>) {
    companion object Factory {
      fun from(values: List<Long>): EncryptedNumbers {
        val nodes = values.map(::Node)
        val linked =
          nodes.zipWithNext { current, next ->
            current.next = next
            next.prev = current
            current
          } + nodes.last()
        val cycled = linked.also { it.first().prev = it.last() }.also { it.last().next = it.first() }
        return EncryptedNumbers(cycled.first(), cycled)
      }
    }

    fun shuffle() {
      original.forEach { num ->
        val distance =
          generateSequence(num.v) { v -> (v % original.size).toInt() + (v / original.size) }
            .first { abs(it) < original.size }
            .toInt()
        num.move(distance)
      }
    }

    fun unlinkAt(v: Long, node: Node<Long>? = head): List<Long> =
      if (node?.v == v) generateSequence(unlinkAt(node)) { it.next }.map { it.v }.toList() else unlinkAt(v, node?.next)

    private fun unlinkAt(node: Node<Long>): Node<Long> = node.also { it.prev?.next = null }.also { it.prev = null }
  }

  private val grooveCoordinates = listOf(1000, 2000, 3000)
  private const val decryptionKey = 811589153L

  fun partOne(lines: List<String>): Long = decryptWithKeyAndShuffle(lines)

  fun partTwo(lines: List<String>): Long = decryptWithKeyAndShuffle(lines, decryptionKey, 10)

  private fun decryptWithKeyAndShuffle(lines: List<String>, key: Long = 1, shuffles: Int = 1): Long {
    val numbers = parseWithKey(lines, key)
    repeat(shuffles) { numbers.shuffle() }
    val unlinked = numbers.unlinkAt(0)
    return grooveCoordinates.map { it % unlinked.size }.sumOf { unlinked[it] }
  }

  private fun parseWithKey(lines: List<String>, key: Long = 1): EncryptedNumbers =
    EncryptedNumbers.from(lines.map(String::toLong).map { it * key })
}
