

plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.plugin.serialization)

    id("com.vanniktech.maven.publish") version "0.36.0"
    id("org.jetbrains.dokka") version "2.2.0"
}

repositories {
    mavenCentral()
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
}

dependencies {
    implementation(libs.postgis.jdbc)
    implementation(libs.postgresql)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.logback.classic)
    implementation(libs.h2gis)
    implementation(libs.dokka.base)
    implementation(libs.kotlin.ajp)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    coordinates("io.github.kavacup.exposed-postgis")
    pom {
        name.set("extension-exposed-postgis")
        description.set("extension-exposed-postgis is a Kotlin library built on top of Exposed to support PostGIS-enabled PostgreSQL databases. This library provides seamless and type-safe integration for spatial data manipulation.")
        url.set("https://github.com/Kavacup/exposed-postgis.git")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://mit-license.org/")
            }
        }
        developers {
            developer {
                id.set("kavacup")
                name.set("Andrej Levitzki")
                email.set("levitzkiandrey@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:https://github.com/Kavacup/exposed-postgis.git")
            developerConnection.set("scm:git:git@github.com:Kavacup/exposed-postgis.git")
            url.set("https://github.com/Kavacup")
        }
    }
}