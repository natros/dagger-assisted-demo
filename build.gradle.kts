import org.jetbrains.gradle.ext.ActionDelegationConfig.TestRunner
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.delegateActions
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
  id("com.github.ben-manes.versions")
  id("nl.littlerobots.version-catalog-update")
  id("org.jetbrains.gradle.plugin.idea-ext")
  application
}

group = "com.github.natros.dagger.assisted.demo"

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.dependencyUpdates {
  checkForGradleUpdate = true
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

tasks.withType<JavaCompile>().configureEach {
  options.release = 17
  options.compilerArgs.add("-parameters")
}

tasks.test {
  useJUnitPlatform()
  maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
  reports {
    html.required = false
    junitXml.required = false
  }
}

idea {
  project {
    settings {
      runConfigurations {
        create<Gradle>("clean") { taskNames = listOf("clean") }
        create<Gradle>("app") { taskNames = listOf("run") }
      }
      delegateActions {
        delegateBuildRunToGradle = true
        testRunner = TestRunner.GRADLE
      }
    }
  }
}

application {
  mainClass = "com.github.natros.dagger.assisted.demo.App"
}

dependencies {
  implementation(libs.annotations)
  implementation(libs.dagger)
  implementation(libs.guava)

  runtimeOnly(libs.logback)

  annotationProcessor(libs.dagger.compiler)
}
