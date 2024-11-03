import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.devtoolsKsp)
}

apply(from = rootProject.file("shared_dependencies.gradle"))

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val properties = gradleLocalProperties(rootDir, providers)
        buildConfigField("String", "TOKEN_MOVIEDB", "\"${properties["TOKEN_MOVIEDB"]}\"")
        buildConfigField("String", "BASE_URL_MOVIEDB", "\"${properties["BASE_URL_MOVIEDB"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    /* retrofit */
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.logging.interceptor)

    //  Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // SQLChiper
    implementation(libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite.ktx)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}