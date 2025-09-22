import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.devtools.ksp)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("17")
    }
}

android {
    namespace = "com.currand60.karooexttemplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.currand60.karooexttemplate"
        minSdk = 23
        targetSdk = 34
        versionCode = 25092201
        versionName = "0.0.0"
        signingConfig = signingConfigs.getByName("debug")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(libs.hammerhead.karoo.ext)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifeycle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)
}

tasks.register("generateManifest") {
    description = "Generates manifest.json with current version information"
    group = "build"

    doLast {
        val manifestFile = file("$projectDir/manifest.json")
        val manifest = mapOf(
            "label" to "Karoo Template",
            "packageName" to android.namespace,
            "latestApkUrl" to "https://github.com/currand/karoo-ext-template/releases/latest/download/app-release.apk",
            "latestVersion" to android.defaultConfig.versionName,
            "latestVersionCode" to android.defaultConfig.versionCode,
            "developer" to "currand",
            "description" to "Template Karoo Extension",
//            "screenshotUrls" to listOf(
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/example1.png",
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/example2.png",
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/example3.png",
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/example4.png",
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/config_screen.png",
//                "https://github.com/currand/karoo-colorspeed/releases/latest/download/config_screen2.png"
//            ),
        )

        val gson = groovy.json.JsonBuilder(manifest).toPrettyString()
        manifestFile.writeText(gson)
        println("Generated manifest.json with version ${android.defaultConfig.versionName} (${android.defaultConfig.versionCode})")
    }
}

tasks.named("assemble") {
    dependsOn("generateManifest")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation(libs.hammerhead.karoo.ext)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifeycle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.preview)
    implementation(libs.androidx.glance.appwidget.preview)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.javax.inject)
    implementation(libs.timber)
    implementation(libs.kotlinx.coroutines.debug)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}