# build.gradle.kts (FINAL VERSION)
plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "com.example"
version = "1.2.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

intellij {
    version.set("2022.3")
    type.set("IC")
    plugins.set(listOf("Git4Idea"))
}

tasks {
    patchPluginXml {
        sinceBuild.set("223")
        untilBuild.set("233.*")
        version.set(project.version.toString())
    }

    buildSearchableOptions {
        enabled = false
    }

    compileJava {
        options.release.set(17)
    }
}
