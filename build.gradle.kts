import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.10"
    application
}

version = "1.0.0"
group = "org.jer.app"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11) // JVM 11 is needed for Desktop
    }

    val doodleVersion = "0.11.1"

    dependencies {
        fun osTarget(): String {
            val osName = System.getProperty("os.name")
            val targetOs = when {
                osName == "Mac OS X" -> "macos"
                osName.startsWith("Win") -> "windows"
                osName.startsWith("Linux") -> "linux"
                else -> error("Unsupported OS: $osName")
            }

            val targetArch = when (val osArch = System.getProperty("os.arch")) {
                "x86_64", "amd64" -> "x64"
                "aarch64" -> "arm64"
                else -> error("Unsupported arch: $osArch")
            }

            return "${targetOs}-${targetArch}"
        }

        // helper to derive OS/architecture pair
        when (osTarget()) {
            "macos-x64" -> implementation("io.nacular.doodle:desktop-jvm-macos-x64:$doodleVersion")
            "macos-arm64" -> implementation("io.nacular.doodle:desktop-jvm-macos-arm64:$doodleVersion")
            "linux-x64" -> implementation("io.nacular.doodle:desktop-jvm-linux-x64:$doodleVersion")
            "linux-arm64" -> implementation("io.nacular.doodle:desktop-jvm-linux-arm64:$doodleVersion")
            "windows-x64" -> implementation("io.nacular.doodle:desktop-jvm-windows-x64:$doodleVersion")
            "windows-arm64" -> implementation("io.nacular.doodle:desktop-jvm-windows-arm64:$doodleVersion")
        }

        // Optional
         implementation ("io.nacular.doodle:controls:$doodleVersion" )
         implementation ("io.nacular.doodle:animation:$doodleVersion")
         implementation ("io.nacular.doodle:themes:$doodleVersion"   )
    }


}

application {
    mainClass.set("JerApplication")
}