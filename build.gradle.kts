plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "com.example"
version = "1.2.0"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
intellij {
    version.set("2023.1.5")
    type.set("PS") // PhpStorm
    plugins.set(listOf("Git4Idea"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    wrapper {
        gradleVersion = "8.4"
    }

    patchPluginXml {
        version.set(project.version.toString())
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    buildSearchableOptions {
        enabled = false
    }

    runIde {
        maxHeapSize = "2g"
    }

    compileJava {
        options.release.set(17)
    }
}
