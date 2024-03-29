@file:Suppress("UNUSE")
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    //alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.application)
    alias(libs.plugins.libres)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    /*
        js {
            browser()
            binaries.executable()
        }
        iosX64()
        iosArm64()
        iosSimulatorArm64()

        cocoapods {
            version = "1.0.0"
            summary = "Compose application framework"
            homepage = "empty"
            ios.deploymentTarget = "11.0"
            podfile = project.file("../iosApp/Podfile")
            framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    */
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(libs.libres)
                implementation(libs.composeImageLoader)
                implementation(libs.napier)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.insetsx)
                implementation(libs.orbit)
                implementation(libs.composeIcons.featherIcons)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.multiplatformSettings)
                implementation(libs.kstore)
                implementation(libs.kotlinx.collections.immutable)
                implementation(libs.arrow.core)
                implementation(libs.arrow.fx.coroutines)
                implementation(libs.arrow.optics)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(libs.kotlinx.coroutines.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }

        /*
                val jsMain by getting {
                    dependencies {
                        implementation(compose.html.core)
                    }
                }
                val iosMain by getting {
                    dependencies {
                    }
                }
        */
    }
}

android {
    val id = "andrew.misterio05.app"
    namespace = id
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = id
        versionCode = 1
        versionName = "1.0.0"

        resValue("string", "app_id", id)
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes.add("META-INF/**")
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "andrew.misterio05.app.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}
/*
compose.experimental {
    web.application {}
}
*/
libres {
    // https://github.com/Skeptick/libres#setup
}
tasks.getByPath("desktopProcessResources").dependsOn("libresGenerateResources")
tasks.getByPath("desktopSourcesJar").dependsOn("libresGenerateResources")
//tasks.getByPath("jsProcessResources").dependsOn("libresGenerateResources")

buildConfig {
  // BuildConfig configuration here.
  // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
