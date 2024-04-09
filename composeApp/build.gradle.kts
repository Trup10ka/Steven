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
        }
    }
}
