plugins {
  kotlin("jvm") version "1.9.10"
  id("com.diffplug.spotless") version "6.22.0"
}

group = "ianvkoeppe"

version = "1.0.0"

kotlin { jvmToolchain(17) }

repositories { mavenCentral() }

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.google.code.gson:gson:+")

  testImplementation(kotlin("test"))
  testImplementation("org.junit.jupiter:junit-jupiter:+")
  testImplementation("org.assertj:assertj-core:+")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
  kotlin { ktfmt().googleStyle() }
  kotlinGradle { ktfmt().googleStyle() }
}

tasks.test {
  useJUnitPlatform()
  systemProperty("junit.jupiter.execution.parallel.enabled", "true")
  systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
}
