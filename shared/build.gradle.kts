plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.koltinSerialization)
}

kotlin {
    
    jvm()

    js(IR) {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
