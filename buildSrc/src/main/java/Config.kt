object Config {
  object AppConfig {
    const val appId = "app.te.currency_app"
    const val compileSdkVersion = 32
    const val minSdkVersion = 23
    const val versionCode = 10
    const val versionName = "1"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  object Dependencies {
    const val jitPackURL = "https://jitpack.io"
    const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
  }

  object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val hilt = "dagger.hilt.android.plugin"
    const val kotlin_extensions = "kotlin-android-extensions"
    const val ktLint = "org.jlleitschuh.gradle.ktlint"
  }

  object Modules {
    const val data = ":data"
    const val prettyPopUp = ":prettyPopUp"
  }
}