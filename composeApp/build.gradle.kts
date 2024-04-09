plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(projects.shared)
        }
    }
}
