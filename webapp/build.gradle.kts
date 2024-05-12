plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    js(IR) {

        browser {
            webpackTask {
                mainOutputFileName = "steven.js"
            }
        }
        binaries.executable()

    }
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }

        jsMain.dependencies {
            implementation(libs.kotlinx.coroutines.js)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
