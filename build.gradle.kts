plugins {
  application
  kotlin("jvm") version "1.9.10"
  id("com.diffplug.spotless") version "6.22.0"
}

group = "ianvkoeppe"

version = "1.0.0"

kotlin { jvmToolchain(17) }

repositories { mavenCentral() }

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.github.ajalt.clikt:clikt:+")
  implementation("com.google.code.gson:gson:+")
  implementation("com.squareup.okhttp3:okhttp:+")

  testImplementation(kotlin("test"))
  testImplementation("org.assertj:assertj-core:+")
  testImplementation("org.junit.jupiter:junit-jupiter:+")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
  kotlin { ktfmt().googleStyle().configure { ktfmt -> ktfmt.setMaxWidth(120) } }
  kotlinGradle { ktfmt().googleStyle().configure { ktfmt -> ktfmt.setMaxWidth(120) } }
}

tasks.test {
  useJUnitPlatform()

  maxHeapSize = "2g"
  systemProperty("junit.jupiter.execution.parallel.enabled", "true")
  systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
  systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
}

tasks.register<JavaExec>("scaffolder") {
  description = "Scaffolds a New Day of Advent of Code."
  classpath = sourceSets["main"].runtimeClasspath
  mainClass = "scaffold.ScaffolderKt"
}
