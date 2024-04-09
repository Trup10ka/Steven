plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    
    jvm()

    js(IR) {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
    }
}
