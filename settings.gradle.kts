enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "exposed-postgis"

include("lib")


pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}