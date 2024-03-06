import java.net.URI

plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

repositories {
    mavenCentral()
}

allprojects {
    version = "1.4"
    group = "com.invoice.frank"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        maven {
            name = "papermc-repo"
            url = URI("https://repo.papermc.io/repository/maven-public/")
        }
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }

    kotlin {
        jvmToolchain(17)
    }

    if (name != "Frank-Common") {
        dependencies {
            implementation(project(":Frank-Common"))
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()

                println("Publishing $artifactId $version to Maven Central ($groupId)")

                from(components["java"])
            }
        }
    }
}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}