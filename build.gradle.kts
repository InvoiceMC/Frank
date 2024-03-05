import java.net.URI

plugins {
    java
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "com.invoice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        url = URI("https://jitpack.io")
        credentials {
            username = findProperty("authToken").toString()
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileJava {
        options.compilerArgs.addAll(listOf("--release", "17"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.invoice"
            artifactId = "frank"
            version = "1.0.0"

            from(components["java"])
        }
    }
}

kotlin {
    jvmToolchain(17)
}