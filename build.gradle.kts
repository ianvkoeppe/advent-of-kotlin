plugins {
  kotlin("jvm") version "1.9.0"
}

group = "ianvkoeppe"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.google.code.gson:gson:2.10.1")

  testImplementation("org.assertj:assertj-core:3.24.2")
  testImplementation(kotlin("test"))
}

kotlin {
  jvmToolchain(17)
}

tasks.test {
  useJUnitPlatform()
  systemProperty("junit.jupiter.execution.parallel.enabled", "true")
  systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
}
