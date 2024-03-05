plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Frank"
include("bukkit")
project(":bukkit").name = "Frank-Bukkit"
include("velocity")
project(":velocity").name = "Frank-Velocity"
include("common")
project(":common").name = "Frank-Common"
