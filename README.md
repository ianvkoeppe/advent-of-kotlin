# Advent of Kotlin

Solutions for [adventofcode.com](https://adventofcode.com) in Kotlin.

## Scaffolding

**Command**

1. Login to [adventofcode.com](https://adventofcode.com) and copy your session cookie to `~/.aoc`. 
2. The following downloads your problem input to `src/test/resources` and generates skeleton classes and tests.
3. Unfortunately extracting example input isn't trivial, so you'll need to manually copy the example input into the generated `example.txt`.

```commandline
> ./gradlew scaffolder --args='--help'

Usage: scaffolder [<options>]

Options:
  --year=<int>    Year of the problem to setup.
  --day=<int>     Day of the problem to setup.
  --open-browser  Open the browser to the chosen problem.
  --force         Force regeneration of classes even if they already exist.
  -h, --help      Show this message and exit
```

**Example Scaffolding**
```kotlin
package y2000

object Day1 {
  fun partOne(lines: List<String>): Int {
    return 0
  }

  fun partTwo(lines: List<String>): Int {
    return 0
  }
}
```

```kotlin
package y2000

import io.Filer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Filer.readExample(2000, 1))).isEqualTo(0)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Filer.readProblem(2000, 1))).isEqualTo(0)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Filer.readExample(2000, 1))).isEqualTo(0)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Filer.readProblem(2000, 1))).isEqualTo(0)
  }
}
```
