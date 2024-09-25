import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.ir.JsIrBinary

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

group = "ai.mealz.analytics"
version = "##VERSION##"
description = "Mealz Shared Analytics"

// if you're using XCode 15 or after, you will need to set
// export MODERN_XCODE_LINKER=true
// in your CLI before running ./gradlew clean && ./gradlew assembleXCFramework
val isModernXcodeLinker = System.getenv("MODERN_XCODE_LINKER")?.toBoolean() ?: false

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    // TODO: try to build with wasmJs
    js(IR) {
        useEsModules()
        generateTypeScriptDefinitions()
        browser {
            webpackTask {
                mainOutputFileName = "main.js"
                sourceMaps = false
            }
            commonWebpackConfig {
                sourceMaps = false
            }
        }
        binaries.withType<JsIrBinary>().all {
            this.linkTask.configure {
                kotlinOptions { sourceMap = false }
            }
        }
        binaries.executable()
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "mealzSharedAnalytics"
            isStatic = true
            if (isModernXcodeLinker) linkerOpts += "-ld64"
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.core)
            implementation(libs.gson)
        }

        jsMain.dependencies {
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.ktor.core)
        }

        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

}

android {
    namespace = "ai.mealz.analytics"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
