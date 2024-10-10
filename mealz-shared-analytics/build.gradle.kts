import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.ir.JsIrBinary

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    id("maven-publish")
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
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    withSourcesJar(false)

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
            baseName = "mealz-shared-analytics"
            isStatic = true
            if (isModernXcodeLinker) linkerOpts += "-ld64"
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
        }

        commonTest.dependencies {
            api(kotlin("test"))
        }

        androidMain.dependencies {
            api(libs.kotlinx.coroutines.android)
            api(libs.ktor.core)
            api(libs.gson)
        }

        jsMain.dependencies {
        }

        iosMain.dependencies {
            api(libs.ktor.client.darwin)
            api(libs.ktor.core)
        }

        iosArm64()
        iosX64()
        iosSimulatorArm64()

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
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    repositories {
        maven {
            name = "github"
            url = uri("${buildDir.absolutePath}/release")
        }
    }

    publications {
        create<MavenPublication>("release") {
            artifactId = "mealz-shared-analytics"
            from(components["kotlin"])
        }
    }
}
