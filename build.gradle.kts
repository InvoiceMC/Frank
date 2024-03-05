plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "com.invoice.frank"
version = "1.0"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }

    kotlin {
        jvmToolchain(17)
    }

    if (name != "common") {
        dependencies {
            implementation(project(":common"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.invoice.frank"
            artifactId = project.name
            version = "1.0"

            from(components["java"])
        }
    }
}

kotlin {
    jvmToolchain(17)
}
