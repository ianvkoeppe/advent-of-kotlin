package y2022

object Day7 {
  interface Path {
    val name: String
    val parent: Dir?
    fun size(): Long
  }
  data class File(override val name: String, override val parent: Dir, val size: Long): Path {
    override fun size(): Long = size
  }
  data class Dir(override val name: String, override val parent: Dir? = null, val children: MutableList<Path> = mutableListOf()): Path {
    override fun size(): Long = children.sumOf { it.size() }
  }
  private const val smallFileThreshold = 100000
  private const val machineMemoryCapacity = 70000000
  private const val memoryNeededForUpdate = 30000000

  fun partOne(lines: List<String>): Long {
    return flattenDirSizes(parse(lines)).filter { it <= smallFileThreshold }.sum()
  }

  fun partTwo(lines: List<String>): Long {
    val paths = parse(lines)
    val additionalFreeSpaceRequired = memoryNeededForUpdate - (machineMemoryCapacity - paths.size())
    return flattenDirSizes(parse(lines)).sorted().find { additionalFreeSpaceRequired - it <= 0 }!!
  }

  private fun parse(lines: List<String>, current: Int = 0, cd: Dir = Dir("")): Dir {
    if (current >= lines.size) return generateSequence(cd) { it.parent }.last()
    return parse(lines, current + 1, parseCommand(lines[current], cd))
  }

  private fun parseCommand(line: String, cd: Dir): Dir {
    val (_, cmd) = line.split(" ")
    return when (cmd) {
      "cd" -> {
        when (val opt = line.split(" ")[2]) {
          ".." -> cd.parent!!
          else -> cd.children.filterIsInstance<Dir>().firstOrNull { it.name == opt } ?: parsePath("dir $opt", cd)
        }
      }
      "ls" -> cd
      else -> parsePath(line, cd)
    }
  }

  private fun parsePath(path: String, cd: Dir): Dir {
    val (first, second) = path.split(" ")
    cd.children.add(if (first == "dir") Dir(second, cd) else File(second, cd, first.toLong()))
    return cd
  }

  private fun flattenDirSizes(path: Path?): List<Long> {
    if (path == null || path !is Dir) return listOf()
    return path.children.flatMap { flattenDirSizes(it) } + path.size()
  }
}