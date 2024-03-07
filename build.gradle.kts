import java.net.URI

val publicationVersion = "1.0.22"

plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

allprojects {
    version = publicationVersion
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
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        api("com.github.InvoiceMC:Munch:e2ee7d2d4e")
    }

    kotlin {
        jvmToolchain(17)
    }

    if (name != "Frank-Common") {
        apply(plugin = "com.github.johnrengelman.shadow")
        dependencies {
            implementation(project(":Frank-Common"))
        }

        tasks {
            build {
                dependsOn("shadowJar")
            }

            shadowJar {
                archiveClassifier.set("")
            }
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
                version = publicationVersion

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