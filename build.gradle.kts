plugins {
    id("dev.kikugie.loom-back-compat")
}

java {
    withSourcesJar()
}

version = "2.8.1"
group = "net.tcpshield.tcpshield"
base {
    archivesName.set("TCPShield")
}


val loaderVersion = project.findProperty("deps.fabric_loader")?.toString() ?: "0.16.13"

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")

    loomx.applyMojangMappings()

    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
}

repositories {
    maven("https://repo1.maven.org/maven2/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://maven.fabricmc.net/")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf(
                "version" to project.version,
                "mc_dep" to (project.findProperty("mod.mc_dep") ?: ">=1.16")
            ))
        }
    }

    compileJava {
        val currentVersion = stonecutter.current.version
        val javaVersion = when {
            currentVersion.startsWith("26.1") -> 25
            currentVersion.startsWith("1.21") -> 21
            currentVersion.startsWith("1.20.5") || currentVersion.startsWith("1.20.6") -> 21
            currentVersion.startsWith("1.20") -> 17
            currentVersion.startsWith("1.19") || currentVersion.startsWith("1.18") -> 17
            else -> 8
        }
        options.release.set(javaVersion)
    }

    jar {
        archiveBaseName.set("TCPShield")
        archiveVersion.set("2.8.1")
    }
}
