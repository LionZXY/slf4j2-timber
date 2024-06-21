plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "uk.kulikov.slf4j.timber"
    compileSdk = 34

    defaultConfig {
        aarMetadata {
            minCompileSdk = 34
        }
        minSdk = 9
        proguardFiles(
            "consumer-proguard-rules.pro"
        )
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        textReport = true
    }
}


dependencies {
    compileOnly(libs.annotations)

    implementation(libs.slf4j)
    implementation(libs.timber)

    testImplementation(libs.festandroid)
    testImplementation(libs.festassert)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}