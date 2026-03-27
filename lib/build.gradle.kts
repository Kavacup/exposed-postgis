plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.plugin.serialization)
    `maven-publish`
    signing
    id("tech.yanand.maven-central-publish") version "1.3.0"
    id("org.jetbrains.dokka") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.postgis:postgis-jdbc:2025.1.1") {
        exclude(module = "postgresql")
    }
    implementation("org.postgresql:postgresql:42.7.10")
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.logback.classic)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:1.13.3")
    implementation("org.orbisgis:h2gis:2.2.3")
//    testImplementation("org.orbisgis:h2gis-functions:1.3.2")

    implementation("org.jetbrains.dokka:dokka-base:1.9.0")
    implementation("org.jetbrains.dokka:kotlin-as-java-plugin:1.9.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputDirectory.set(buildDir.resolve("dokka"))
    dokkaSourceSets {
        configureEach {
            includeNonPublic.set(false)
            skipDeprecated.set(true)
        }
    }
}

tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}