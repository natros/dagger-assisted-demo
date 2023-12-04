pluginManagement {
  plugins {
    id("com.gradle.enterprise") version "3.15.1"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("nl.littlerobots.version-catalog-update") version "0.8.1"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
  }
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins {
  id("com.gradle.enterprise")
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

rootProject.name = "dagger-assisted-demo"

